package patientcaretrackbackend.adapters.web.dto;

import patientcaretrackbackend.domain.model.*;

public record RegistroRequest(
        Long pacienteId,
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
