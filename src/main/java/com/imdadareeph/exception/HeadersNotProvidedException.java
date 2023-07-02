package com.imdadareeph.exception;

import com.imdadareeph.error.DomainError;
import com.imdadareeph.error.DomainException;
import com.imdadareeph.error.DomainBaseException;
import org.springframework.http.HttpStatus;

import java.util.Collection;


/**
 * @author Mohd Imdad Areeph
 *
 */
@DomainException(
    errorSequence = "001",
    errorMessage = "No moduleName and or No channelName and or correlationId in header",
    errorHttpStatus = HttpStatus.BAD_REQUEST
)
public class HeadersNotProvidedException extends DomainBaseException {

    private static final long serialVersionUID = 1560957983115500932L;

    /**
     * Instantiates a new Tracing headers not provided exception.
     *
     * @param errors the errors
     */
    public HeadersNotProvidedException(final Collection<DomainError> errors) {
        super(errors);
    }

    /**
     * Instantiates a new Tracing headers not provided exception.
     *
     * @param cause the cause
     */
    public HeadersNotProvidedException(final String cause) {
        super(cause);
    }
}
