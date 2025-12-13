package patientcaretrackbackend.patients.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer edad;

    @Column(length = 200)
    private String historialMedico;

    @Column(length = 200)
    private String observacionesGenerales;

    private String imageUrl;
    private Long assignedUserId;
}
