package com.imdadareeph.router;

import com.imdadareeph.errorhandler.ErrorHandler;
import com.imdadareeph.handlers.OrderServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.imdadareeph.Constant.BACK_SLASH;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class ServiceApplicationRouter
{

    private final OrderServiceHandler orderServiceHandler;

    /**
     * Represents a ServiceApplicationRouter function that routes to a
     *
     * @param errorHandler {@code ErrorHandler} to handle any custom error
     * @return a {@code RouterFunction<ServerResponse>} after processing {@code ServerRequest}
     */
    public RouterFunction<ServerResponse> doRoute(final ErrorHandler errorHandler) {

        return route(GET(BACK_SLASH + "order-service/"), orderServiceHandler::getDetails)
                .andRoute(POST(BACK_SLASH+"order-service/status"), orderServiceHandler::getStatus);
    }
}
