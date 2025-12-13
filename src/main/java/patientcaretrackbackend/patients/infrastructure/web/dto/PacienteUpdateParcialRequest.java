package patientcaretrackbackend.patients.infrastructure.web.dto;

public record PacienteUpdateParcialRequest(
        Boolean desayuno,
        Boolean comida,
        Boolean cena,
        Boolean hizoDeposicion,
        String tipoDeposicion,   // "BLANDA", "NORMAL", "FECALOMA"
        Integer liquidosMl,
        String aseoTipo,         // "DUCHA" o "ASEO"
        String notas
) {}
