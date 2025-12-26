package patientcaretrackbackend.patients.application.dto;

import java.time.LocalDate;

public record AlertDto(
        String type,        // "NO_MEALS" | "INCIDENT"
        Long patientId,
        String patientName,
        LocalDate date,
        String message
) {}
