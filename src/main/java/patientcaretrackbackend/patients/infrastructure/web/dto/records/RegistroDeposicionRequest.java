package patientcaretrackbackend.patients.infrastructure.web.dto.records;

import jakarta.validation.constraints.NotNull;
import patientcaretrackbackend.patients.domain.model.DeposicionCalidad;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;

public record RegistroDeposicionRequest(
        @NotNull TipoRegistro tipo,          // DEPOSICION
        @NotNull Boolean hizoDeposicion,
        DeposicionCalidad deposicionCalidad,
        String comentario,
        Boolean notificarIncidencia
) implements RegistroBaseRequest {}
