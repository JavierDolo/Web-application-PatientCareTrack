package patientcaretrackbackend.patients.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paciente {

    private Long id;

    private String nombre;
    private Integer edad;
    private String historialMedico;
    private String observacionesGenerales;

    // Imagen (el front puede mandar una URL o nombre de fichero)
    private String imageUrl;

    // Usuario asignado (en dominio, referencia por id)
    private Long assignedUserId;
}
