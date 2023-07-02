package com.imdadareeph.exception;

import com.imdadareeph.error.DomainError;
import com.imdadareeph.error.DomainException;
import com.imdadareeph.error.DomainBaseException;
import org.springframework.http.HttpStatus;

import java.util.Collection;

/**
 * @author Imdad Areeph
 *
 */
@DomainException(
        errorMessage = "Request provided is not valid.",
        errorHttpStatus = HttpStatus.BAD_REQUEST,
        errorSequence = "001")
public class DomainValidationException extends DomainBaseException {

    private static final long serialVersionUID = -910165772138345485L;

    /**
     * Instantiates a new Domain base exception.
     *
     * @param errors the errors
     */
    public DomainValidationException(final Collection<DomainError> errors) {
        super(errors);
    }

    /**
     * Instantiates a new Domain base exception.
     *
     * @param cause the cause
     */
    public DomainValidationException(final String cause) {
        super(cause);
    }

}
