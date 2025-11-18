package patientcaretrackbackend.domain.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registro {
    private Long id;
    private Long pacienteId;
    private Long creadoPorUserId;
    private TipoRegistro tipo;
    private String comentario;
    private Instant createdAt;

    private Boolean hizoDeposicion;
    private DeposicionCalidad deposicionCalidad;

    private Boolean hizoDesayuno;
    private Boolean hizoComida;
    private Boolean hizoCena;

    private Integer liquidosMl;

    private AseoTipo aseoTipo;

    private Boolean notificarIncidencia;
}
