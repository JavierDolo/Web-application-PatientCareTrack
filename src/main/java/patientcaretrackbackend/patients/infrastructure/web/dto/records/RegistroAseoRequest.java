package patientcaretrackbackend.patients.infrastructure.web.dto.records;

import jakarta.validation.constraints.NotNull;
import patientcaretrackbackend.patients.domain.model.AseoTipo;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;

public record RegistroAseoRequest(
        @NotNull TipoRegistro tipo,          // ASEO
        @NotNull AseoTipo aseoTipo,
        String comentario,
        Boolean notificarIncidencia
) implements RegistroBaseRequest {}
