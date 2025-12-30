package patientcaretrackbackend.patients.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class Alert {
    private Long id;

    private AlertType type;
    private AlertStatus status;

    private Long pacienteId;
    private Long createdByUserId;   // quién provocó/creó (USER), opcional
    private String message;

    private LocalDate forDate;      // para NO_MEALS (día evaluado)
    private String dedupeKey;       // clave para no duplicar

    private Instant createdAt;
    private Instant resolvedAt;
}
