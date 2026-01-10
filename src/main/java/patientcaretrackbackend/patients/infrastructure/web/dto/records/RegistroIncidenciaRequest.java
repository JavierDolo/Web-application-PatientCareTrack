package patientcaretrackbackend.patients.infrastructure.web.dto.records;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;

@Schema(name = "RegistroIncidenciaRequest")
public record RegistroIncidenciaRequest(
        @NotNull TipoRegistro tipo,      // INCIDENCIA
        @NotBlank String comentario,     // obligatorio
        Boolean notificarIncidencia
) implements RegistroBaseRequest {}
