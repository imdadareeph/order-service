package com.imdadareeph.errorhandler;

import com.imdadareeph.error.DomainError;
import com.imdadareeph.error.DomainException;
import com.imdadareeph.exception.ThrowableTranslator;
import com.imdadareeph.Constant;
import com.imdadareeph.exception.HeadersNotProvidedException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.MethodNotAllowedException;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author Mohd Imdad Areeph
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ErrorHandler
{
    private static final String FUNCTIONAL_ERROR_MESSAGE = "Error Occurred while calling service";
    private final ThrowableTranslator throwableTranslator;

    /**
     * @return {@code Consumer<Throwable>}
     */
    private Consumer<Throwable> stackTraceLogging() {
        return (Throwable stackTrace) ->
            log.error("{} : {}", FUNCTIONAL_ERROR_MESSAGE, stackTrace.getStackTrace());
    }

    /**
     * @param monoError error code
     * @return
     */
    private <T extends Throwable> Mono<ServerResponse> getResponse(final Mono<T> monoError) {
        return monoError
            .flatMap(throwable -> throwableTranslator.apply(throwable)
                .flatMap(domainError -> ServerResponse
                    .status(Optional.ofNullable(domainError)
                        .map(DomainError::getHttpStatus)
                        .orElse(DomainException.DEFAULT_HTTP_STATUS)
                    ).body(fromObject(domainError))));
    }

    /**
     * Method not allowed mono.
     *
     * @param request the request
     * @return {@code Mono<ServerResponse>} if error response the create the new error
     */
    public Mono<ServerResponse> methodNotAllowed(final ServerRequest request) {
        return Mono.just(new MethodNotAllowedException("", null))
            .transform(this::getResponse);
    }

    /**
     * Throwable error mono.
     *
     * @param error the error
     * @return {@code Mono<ServerResponse>} the mono
     */
    public Mono<ServerResponse> throwableError(final Throwable error) {
        log.error("{} {} {}", Constant.ERROR_MESSAGE_STACK,error, Arrays.asList(error.getStackTrace()).stream().map(Objects::toString).collect(Collectors.joining(Constant.BACK_SLASH_N)));
        return Mono.just(error).doOnNext(stackTraceLogging()).transform(this::getResponse);
    }

    /**
     * Headers not provided mono.
     *
     * @param request Represents a server-side HTTP request
     * @return a {@code Mono<ServerResponse>}
     */
    public Mono<ServerResponse> headersNotProvided(final ServerRequest request) {
        log.info(request.path());
        return Mono.just(new HeadersNotProvidedException(""))
            .transform(this::getResponse);
    }

    /**
     * <p>
     *     print the error stack in logs
     * </p>
     * @param error error object
     */
    private void printErrorInLog(Throwable error) {
        log.error("{} {} {}", Constant.ERROR_MESSAGE_STACK,error,
                Arrays.asList(error.getStackTrace())
                        .stream().map(Objects::toString)
                        .collect(Collectors.joining(Constant.BACK_SLASH_N)));
    }
}
