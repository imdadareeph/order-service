package com.imdadareeph.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Optional;

/**
 * The type Domain error.
 */
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class DomainClientError {

    private String             errorMessage;
    private Collection<String> errorRecords;

    @JsonIgnore private HttpStatus    httpStatus;
    @JsonIgnore private String        errorSequence;
    @JsonIgnore private ErrorLocation errorLocation;

    /**
     * Gets error code.
     *
     * @return the error code
     */
    @JsonProperty("errorCode")
    public String getErrorCode() {
        return Optional.ofNullable(httpStatus)
                .map(HttpStatus::value)
                .orElse(0)
                + "."
                + Optional.ofNullable(errorLocation)
                .map(ErrorLocation::getCode)
                .orElse("0")
                + "."
                + errorSequence;
    }

    /**
     * The enum Location.
     */
    public enum Location implements ErrorLocation {

        /**
         * DB location.
         */
        DB("032"),
        /**
         * Service location.
         */
        SERVICE("031"),
        /**
         * Gateway location.
         */
        GATEWAY("030");

        private String code;

        Location(final String code) {
            this.code = code;
        }

        /**
         * Code string.
         *
         * @return the string
         */
        public String getCode() {
            return code;
        }
    }

}

