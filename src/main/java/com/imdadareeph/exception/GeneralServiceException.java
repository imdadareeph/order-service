package com.imdadareeph.exception;

import com.imdadareeph.error.DomainError;
import com.imdadareeph.error.DomainException;
import com.imdadareeph.error.DomainBaseException;
import org.springframework.http.HttpStatus;

import java.util.Collection;

/**
 * The type General service exception.
 */
@DomainException(
    errorMessage = "Unhandled exception has occurred. Please contact Support Team. ",
    errorHttpStatus = HttpStatus.SERVICE_UNAVAILABLE,
    errorSequence = "001")
public class GeneralServiceException extends DomainBaseException {

    private static final long serialVersionUID = -6210966549387641412L;

    /**
     * Instantiates a new Domain base exception.
     *
     * @param errors the errors
     */
    public GeneralServiceException(final Collection<DomainError> errors) {
        super(errors);
    }

    /**
     * Instantiates a new Domain base exception.
     *
     * @param cause the cause
     */
    public GeneralServiceException(final String cause) {
        super(cause);
    }
}
