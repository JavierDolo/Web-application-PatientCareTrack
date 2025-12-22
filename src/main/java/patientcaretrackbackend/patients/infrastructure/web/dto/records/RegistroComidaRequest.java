package patientcaretrackbackend.patients.infrastructure.web.dto.records;

import jakarta.validation.constraints.NotNull;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;

public record RegistroComidaRequest(
        @NotNull TipoRegistro tipo,          // DESAYUNO | COMIDA | CENA
        @NotNull Boolean realizado,
        String comentario,
        Boolean notificarIncidencia
) implements RegistroBaseRequest {}
