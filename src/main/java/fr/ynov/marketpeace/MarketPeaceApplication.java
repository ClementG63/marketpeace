package fr.ynov.marketpeace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@Configuration
@EntityScan("fr.ynov.marketpeace.entities")
@EnableJpaRepositories("fr.ynov.marketpeace.repositories")
@ConfigurationPropertiesScan
public class MarketPeaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketPeaceApplication.class, args);
    }
}
