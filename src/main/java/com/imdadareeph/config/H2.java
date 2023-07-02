package com.imdadareeph.config;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("h2")
public class H2 {

    private Server webServer;

    @Value("${orderservice.api.h2-console-port}")
    Integer h2ConsolePort;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        log.info("starting h2 console at port "+h2ConsolePort);
        this.webServer = Server.createWebServer("-webPort", h2ConsolePort.toString(), "-tcpAllowOthers").start();
        log.info("h2 db url is  "+webServer.getService().getURL());
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("stopping h2 console at port "+h2ConsolePort);
        if(this.webServer!=null){
            this.webServer.stop();
        }

    }

}