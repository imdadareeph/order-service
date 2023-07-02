package com.imdadareeph.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.imdadareeph.error.DomainError;
import com.imdadareeph.error.DomainException;
import com.imdadareeph.error.DomainBaseException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * The type Generic throwable translator.
 *
 * @author Imdad Areeph
 */
@Getter
@Slf4j
@Component
public class GenericThrowableTranslator {

    private static final String SEQUENCE1 = "001";

    /**
     * The exception translation.
     */

    public static final Function<Throwable, Optional<DomainError>> exceptionTranslation = throwable ->
        Optional.ofNullable(throwable)
            .filter(test -> test instanceof DomainBaseException)
            .map(value -> (DomainBaseException) value)
            .map((DomainBaseException value) -> {
                final DomainError.DomainErrorBuilder builder = DomainError.builder();
                value.getErrors().stream().findFirst().ifPresent((DomainError domainError) ->
                    builder
                        .errorCode(domainError.getErrorCode())
                        .errorMessage(domainError.getErrorMessage())
                        .errorSequence(domainError.getErrorSequence())
                        .httpStatus(domainError.getHttpStatus())
                );

                builder.errorRecords(value.getErrors().stream().skip(1)
                    .map(DomainError::getErrorMessage).collect(Collectors.toList()));
                return builder.build();
            });

    /**
     * The Generic exceptions extension.
     */
    public static final Function<Throwable, Optional<DomainError>> genericExceptionsExtension = (Throwable throwable) -> {

        DomainError domainError = null;

        if (throwable instanceof DecodingException
            || throwable instanceof InvalidFormatException
            || throwable instanceof ServerWebInputException
            || throwable instanceof HeadersNotProvidedException) {
            domainError = DomainError.builder()
                .errorMessage("Request provided is not valid. " + throwable.getMessage())
                .httpStatus(BAD_REQUEST)
                .errorSequence(SEQUENCE1)
                .build();
        } else if (throwable instanceof IllegalArgumentException) {
            domainError = DomainError.builder()
                .errorMessage(throwable.getMessage())
                .httpStatus(BAD_REQUEST)
                .errorSequence(SEQUENCE1)
                .build();
        }
        return Optional.ofNullable(domainError);
    };

    /**
     * The Fallback translation.
     */
    public static final Function<Throwable, DomainError> fallbackTranslation = throwable -> {
        log.error("{}","Uncaught exception :", throwable);
        return DomainError.builder()
            .errorMessage("Unhandled exception has occurred. Please contact Support Team")
            .httpStatus(DomainException.DEFAULT_HTTP_STATUS)
            .errorSequence(DomainException.DEFAULT_SEQUENCE)
            .build();
    };

    /**
     * The Generic translation.
     */
    public static final Function<Throwable, DomainError> genericTranslation = (Throwable throwable) -> exceptionTranslation.apply(throwable)
        .orElseGet(() -> genericExceptionsExtension.apply(throwable)
            .orElseGet(() -> fallbackTranslation.apply(throwable)));

}