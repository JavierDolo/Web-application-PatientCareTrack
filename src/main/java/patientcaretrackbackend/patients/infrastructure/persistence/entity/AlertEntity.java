package patientcaretrackbackend.patients.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import patientcaretrackbackend.patients.domain.model.AlertStatus;
import patientcaretrackbackend.patients.domain.model.AlertType;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AlertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AlertType type;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    private Long pacienteId;
    private Long createdByUserId;

    @Column(length = 1000)
    private String message;

    private LocalDate forDate;

    @Column(length = 200, unique = true)
    private String dedupeKey;

    private Instant createdAt;
    private Instant resolvedAt;
}
