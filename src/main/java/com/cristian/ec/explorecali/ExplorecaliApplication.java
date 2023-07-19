package com.cristian.ec.explorecali;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Explore California API",
                description = "API definitions for the Explore California Microservice"
        )
)
public class ExplorecaliApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExplorecaliApplication.class, args);
    }
}
