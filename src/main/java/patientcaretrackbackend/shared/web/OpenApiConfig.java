package patientcaretrackbackend.shared.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Patient Care Track API",
                version = "1.0",
                description = "API para gestión de pacientes y cuidados en residencia"
        )
)
public class OpenApiConfig {
}
