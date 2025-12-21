package patientcaretrackbackend.patients.application.dto;

public record PacienteSummaryDto(
        Long id,
        String nombre,
        Integer edad,
        String imageUrl,
        Long assignedUserId
) {}
