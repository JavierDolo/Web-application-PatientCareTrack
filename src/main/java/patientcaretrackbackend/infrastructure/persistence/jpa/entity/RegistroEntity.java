package patientcaretrackbackend.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import patientcaretrackbackend.domain.model.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pacienteId;
    private Long creadoPorUserId;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipo;

    @Column(length = 1000)
    private String comentario;

    private Instant createdAt;

    private Boolean hizoDeposicion;

    @Enumerated(EnumType.STRING)
    private DeposicionCalidad deposicionCalidad;

    private Boolean hizoDesayuno;
    private Boolean hizoComida;
    private Boolean hizoCena;

    private Integer liquidosMl;

    @Enumerated(EnumType.STRING)
    private AseoTipo aseoTipo;

    private Boolean notificarIncidencia;
}
