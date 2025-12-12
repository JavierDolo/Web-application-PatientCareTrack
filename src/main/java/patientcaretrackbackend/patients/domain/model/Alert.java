package patientcaretrackbackend.patients.domain.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
    private Long id;
    private Long pacienteId;
    private Long generadoPorUserId;
    private String mensaje;
    private boolean resuelta;
    private Instant createdAt;
}
