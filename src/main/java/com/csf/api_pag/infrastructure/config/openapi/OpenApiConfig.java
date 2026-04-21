package com.csf.api_pag.infrastructure.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class OpenApiConfig {

    @Bean
    public OpenAPI pagModuloOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PagModulo API")
                        .description("API para operacoes de checkout e pedidos com integracao PagBank.")
                        .version("v1")
                        .contact(new Contact().name("Time PagModulo")));
    }

    @Bean
    public GroupedOpenApi checkoutGroup() {
        return GroupedOpenApi.builder()
                .group("checkout")
                .pathsToMatch("/api/v1/checkouts/**", "/api/v1/webhook/pagbank/update")
                .build();
    }

    @Bean
    public GroupedOpenApi orderGroup() {
        return GroupedOpenApi.builder()
                .group("order")
                .pathsToMatch("/api/v1/webhook/pagbank/payment-update")
                .build();
    }
}
