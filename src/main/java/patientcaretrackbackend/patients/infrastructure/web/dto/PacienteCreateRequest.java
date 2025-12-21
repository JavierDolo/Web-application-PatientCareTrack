package patientcaretrackbackend.patients.infrastructure.web.dto;

public record PacienteCreateRequest(
        String nombre,
        Integer edad,
        String historialMedico,
        String observacionesGenerales,
        String imageUrl,
        Long assignedUserId
) {}
