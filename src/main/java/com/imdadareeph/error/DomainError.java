package com.imdadareeph.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.function.Function;

/**
 * The type Domain error.
 * @author Mohd Imdad Areeph
 */
@Getter
@Data
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class DomainError implements Function<DomainError.Location, DomainError> {

    private String errorCode;
    private String errorMessage;
    private Collection<String> errorRecords;

    @JsonIgnore
    private HttpStatus httpStatus;
    @JsonIgnore
    private String errorSequence;


    /**
     * Applies this function to the given argument.
     *
     * errorLocation the function argument the function result
     */
    @Override
    public DomainError apply(final Location errorLocation) {
        return toBuilder()
                .errorCode(new StringBuilder()
                        .append(this.httpStatus.value()).append(".")
                        .append(errorLocation.code).append(".")
                        .append(this.errorSequence)
                        .toString())
                .build();
    }

    public enum Location {

        DATABASE("001"),
        GATEWAY("021"),
        SERVICE("031");

        private String code;

        Location(final String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }
    }


}

