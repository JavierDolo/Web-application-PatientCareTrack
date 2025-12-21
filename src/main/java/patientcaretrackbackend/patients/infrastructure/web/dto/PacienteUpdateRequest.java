package patientcaretrackbackend.patients.infrastructure.web.dto;

public record PacienteUpdateRequest(
        String nombre,
        Integer edad,
        String historialMedico,
        String observacionesGenerales,
        String imageUrl,
        Long assignedUserId
) {}
