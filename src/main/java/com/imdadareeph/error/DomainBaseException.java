package com.imdadareeph.error;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Domain base exception.
 * @author Mohd Imdad Areeph
 *
 */
@Getter
public class DomainBaseException extends RuntimeException {

    private static final long serialVersionUID = -8151947739735047444L;
    private final Collection<DomainError> errors           = new ArrayList<>();

    /**
     * Instantiates a new Domain base exception.
     *
     * @param errors the errors
     */
    protected DomainBaseException(final Collection<DomainError> errors) {
        super(Optional.ofNullable(errors)
            .map(domainErr -> domainErr.stream()
                .map(DomainError::getErrorMessage)
                .collect(Collectors.joining(", ")))
            .orElse(DomainException.DEFAULT_MESSAGE));

        initialise(Optional.empty());
        Optional.ofNullable(errors).ifPresent(this.errors::addAll);
    }

    protected DomainBaseException(DomainError error) {
        super(Optional.ofNullable(error)
            .map(DomainError::getErrorMessage)
            .orElse(DomainException.DEFAULT_MESSAGE));

        initialise(Optional.empty());
        Optional.ofNullable(error).ifPresent(this.errors::add);
    }

    /**
     * Instantiates a new Domain base exception.
     *
     * @param cause the cause
     */
    protected DomainBaseException(final String cause) {
        super(new RuntimeException(cause));
        initialise(Optional.of(super.getCause()));
    }


    /**
     * Instantiates a new Domain base exception.
     *
     * @param cause the cause
     */
    protected DomainBaseException(final String cause, final Throwable error) {
        super(new RuntimeException(cause,error));
        initialise(Optional.of(super.getCause()));
    }

    /**
     * Instantiates a new Domain base exception.
     *
     * @param throwable the cause
     */
    protected DomainBaseException(final Throwable throwable) {
        super(new RuntimeException(throwable));
        initialise(Optional.of(super.getCause()));
    }

    private void initialise(final Optional<Throwable> optionalThrowable) {

        final Class<?> exception = getClass();
        if (exception.isAnnotationPresent(DomainException.class)) {
            final DomainException annotation = exception.getAnnotation(DomainException.class);
            errors.add(DomainError.builder()
                .errorMessage(new StringBuilder()
                    .append(annotation.errorMessage())
                    .append(optionalThrowable.map(Throwable::getMessage).orElse(""))
                    .toString())
                .httpStatus(annotation.errorHttpStatus())
                .errorSequence(annotation.errorSequence())
                .build());
        }
        else {
            errors.add(DomainError.builder()
                .errorMessage(DomainException.DEFAULT_MESSAGE)
                .httpStatus(DomainException.DEFAULT_HTTP_STATUS)
                .errorSequence(DomainException.DEFAULT_SEQUENCE)
                .build());
        }
    }

}