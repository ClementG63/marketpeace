package fr.ynov.marketpeace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "openapi")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class OpenApiProperties {

    private final String projectTitle;
    private final String projectDescription;
    private final String projectVersion;
}