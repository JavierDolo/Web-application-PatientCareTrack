package patientcaretrackbackend.patients.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {
    private Long id;
    private String nombre;
    private int edad;
    private String datosMedicos;
}
