package fr.ynov.marketpeace.utils;

import fr.ynov.marketpeace.model.OpenApiProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
@AllArgsConstructor
public class OpenApiConfig {

    private static final String SCHEME_NAME = "bearerAuth";
    private static final String SCHEME = "bearer";

    @Bean
    public OpenAPI customOpenAPI(OpenApiProperties properties) {
        return new OpenAPI()
                .info(getInfo(properties))
                .components(new Components().addSecuritySchemes(SCHEME_NAME, createSecurityScheme()))
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));
    }

    private Info getInfo(OpenApiProperties properties) {
        return new Info()
                .title(properties.getProjectTitle())
                .description(properties.getProjectDescription())
                .version(properties.getProjectVersion())
                .license(getLicense());
    }

    private License getLicense() {
        return new License()
                .name("Unlicense")
                .url("https://unlicense.org/");
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme(SCHEME);
    }
}
