package com.imdadareeph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@ComponentScan("com.imdadareeph.*")
public class OrderServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApiApplication.class, args);
    }


}
