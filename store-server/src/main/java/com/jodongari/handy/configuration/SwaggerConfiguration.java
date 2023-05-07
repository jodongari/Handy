package com.jodongari.handy.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-store")
                .pathsToMatch("/api/**")
                .build();
    }
    @Bean
    public OpenAPI handyStoreApi() {
        return new OpenAPI()
                .info(new Info().title("Handy Store API")
                        .description("Handy Store API Interface docs.")
                        .version("v0.0.1"));
    }
}
