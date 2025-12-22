package patientcaretrackbackend.patients.infrastructure.web.dto.records;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;

public record RegistroLiquidosRequest(
        @NotNull TipoRegistro tipo,          // LIQUIDOS
        @NotNull @Min(0) Integer liquidosMl,
        String comentario,
        Boolean notificarIncidencia
) implements RegistroBaseRequest {}
