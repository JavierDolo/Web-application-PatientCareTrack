package patientcaretrackbackend.patients.infrastructure.web.dto;

import patientcaretrackbackend.patients.domain.model.AseoTipo;
import patientcaretrackbackend.patients.domain.model.DeposicionCalidad;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;

public record RegistroRequest(
        TipoRegistro tipo,
        String comentario,
        Boolean hizoDeposicion,
        DeposicionCalidad deposicionCalidad,
        Boolean hizoDesayuno,
        Boolean hizoComida,
        Boolean hizoCena,
        Integer liquidosMl,
        AseoTipo aseoTipo,
        Boolean notificarIncidencia
) {}
