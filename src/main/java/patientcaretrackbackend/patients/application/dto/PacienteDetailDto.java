package patientcaretrackbackend.patients.application.dto;

public record PacienteDetailDto(
        Long id,
        String nombre,
        Integer edad,
        String historialMedico,
        String observacionesGenerales,
        String imageUrl,
        Long assignedUserId
) {}
