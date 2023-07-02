package com.imdadareeph.handlers;

import com.imdadareeph.errorhandler.ErrorHandler;
import com.imdadareeph.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderServiceHandler {

    private final ErrorHandler errorHandler;
    private final ApplicationUtil applicationUtil;

    public Mono<ServerResponse> getDetails(ServerRequest req) {
        Mono<List<String>> listMono = Flux.just("Imdad", "Areeph").collectList();

        return listMono.flatMap(str -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(str));
    }

    public Mono<ServerResponse> getStatus(ServerRequest serverRequest) {
        Mono<List<String>> listMono = Flux.just("Status", "success").collectList();

        return listMono.flatMap(str -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(str));
    }
}
