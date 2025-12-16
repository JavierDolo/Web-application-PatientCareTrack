package patientcaretrackbackend.shared.persistence.seed;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "seed_runs")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SeedRunEntity {

    @Id
    @Column(length = 100)
    private String seedKey;

    private Instant executedAt;
}
