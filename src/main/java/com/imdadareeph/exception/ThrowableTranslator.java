
package com.imdadareeph.exception;

import com.imdadareeph.error.DomainError;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The type Throwable translator.
 * @author S905488
 *
 */
@Component
public class ThrowableTranslator extends GenericThrowableTranslator implements Function<Throwable, Mono<DomainError>> {

    private final BiFunction<Throwable, DomainError, DomainError> locationDefinition = (throwable, domainError) -> {

        DomainError.Location errorLocation;
        switch (throwable.getClass().getName()) {
            case "java.sql.SQLException":
                errorLocation = DomainError.Location.DATABASE;
                break;
            default:
                errorLocation = DomainError.Location.SERVICE;
                break;
        }
        domainError = domainError.apply(errorLocation);
        return domainError;
    };

    @Override
    public Mono<DomainError> apply(final Throwable throwable) {
        return Mono.just(
                locationDefinition.apply(throwable, genericTranslation.apply(throwable))
        );
    }
}
