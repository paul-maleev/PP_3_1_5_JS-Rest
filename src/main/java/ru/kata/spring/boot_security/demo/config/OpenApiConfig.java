package ru.kata.spring.boot_security.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryProject() {
        return new OpenAPI()
                .info(new Info()
                        .description("Практическая задача 3.1.5 Rest controllers ")
                        .title("Практическая задача 3.1.5 Rest controllers")
                        .version("v1.0")
                        .contact(new Contact().name("Maleev P"))
                );
    }
}

