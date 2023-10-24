package com.gerenciamentofaculdade.gerenciamentofaculdade.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("gerenciamento-faculdade")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springFaculdadeOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Faculdade API")
                        .description("API para gerenciamento de instituições de ensino superior")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
                /*
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
                        */
    }
}
