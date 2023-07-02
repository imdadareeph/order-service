package com.imdadareeph.router;

import com.imdadareeph.errorhandler.ErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@Configuration
@EnableWebFlux
public class ApplicationRouter
{
    /**
     * <p>
     *   This the main router for any api which expose by orderservice
     * </p>
     *
     * @param apiRouter serviceRouter Object
     * @param errorHandler errorHandler object
     * @return {@code RouterFunction<ServerResponse>}
     */
    @Bean
    RouterFunction<ServerResponse> mainRouterFunction(final ApiRouter apiRouter,
        final ErrorHandler errorHandler)
    {
        final Consumer<ServerRequest> requestConsumer = serverRequest ->
            log.info("Request path {} params {}", serverRequest.path(),serverRequest.queryParams().toString());

        return apiRouter.doRoute(errorHandler).filter((request, next) -> {
            requestConsumer.accept(request);

            return Optional.ofNullable(request.headers().asHttpHeaders().toSingleValueMap())
                .map(Map::keySet).map(headerKeySet ->
                {
                    final boolean tracingHeaderAvailable =
                        Optional.of(headerKeySet.containsAll(ServiceApiHeaderType.getHeaderKeys()))
                            .filter(Boolean::booleanValue)
                            .isPresent();
                    return tracingHeaderAvailable ?
                        next.handle(request) : errorHandler.headersNotProvided(request);
                })
                .orElse(errorHandler.headersNotProvided(request));
        });
    }
}
