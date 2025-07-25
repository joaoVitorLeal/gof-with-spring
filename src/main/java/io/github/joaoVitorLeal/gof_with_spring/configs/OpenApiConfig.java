package io.github.joaoVitorLeal.gof_with_spring.configs;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Santander Open Academy — GoF with Spring Boot — REST API Demonstration",
                version = "1.1.0",
                description = """
            This project demonstrates the use of GoF (Gang of Four) design patterns
            in a Spring Boot RESTful API, developed as part of the DIO and Santander Open Academy initiative.
            Source code: * Not yet available *
            """,
                termsOfService = "https://opensource.org/licenses/MIT",
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                ),
                contact = @Contact(
                        name = "João Leal",
                        email = "joaoleal98@outlook.com",
                        url = "https://github.com/joaoVitorLeal"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "LinkedIn — Professional Profile",
                url = "https://linkedin.com/in/joaovlc"
        )
)
public class OpenApiConfig {
}
