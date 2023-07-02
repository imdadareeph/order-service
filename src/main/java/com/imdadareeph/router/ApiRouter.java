package com.imdadareeph.router;

import com.imdadareeph.errorhandler.ErrorHandler;
import com.imdadareeph.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.function.Predicate;

import static com.imdadareeph.Constant.BACK_SLASH;
import static com.imdadareeph.model.Constants.APPLICATION_PATH;
import static com.imdadareeph.model.Constants.SERVICE_NAME_HEADER_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApiRouter {
    private final ServiceApplicationRouter serviceApplicationRouter;

    public RouterFunction<ServerResponse> doRoute(final ErrorHandler errorHandler) {
        log.info("ApiRouter :: doRoute");
        return //nest(headers(headerPredicate),
                nest(path(BACK_SLASH + APPLICATION_PATH)
                                .and(accept(APPLICATION_JSON)),
                        serviceApplicationRouter.doRoute(errorHandler)); //);
    }

    //Postman - pass header serviceName as orderservice
    private final Predicate<ServerRequest.Headers> headerPredicate = headers -> headers
            .header(ServiceApiHeaderType.SERVICE_NAME.getHeaderKey())
            .stream()
            .anyMatch(ApplicationUtil.areBothEqualPredicate(SERVICE_NAME_HEADER_VALUE));
}
