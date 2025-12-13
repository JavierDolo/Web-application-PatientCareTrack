package patientcaretrackbackend.patients.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.PacienteEntity;

@Component
public class PacienteMapper {

    public Paciente toDomain(PacienteEntity e) {
        if (e == null) return null;

        return Paciente.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .edad(e.getEdad())
                .historialMedico(e.getHistorialMedico())
                .observacionesGenerales(e.getObservacionesGenerales())
                .imageUrl(e.getImageUrl())
                .assignedUserId(e.getAssignedUserId())
                .build();
    }

    public PacienteEntity toEntity(Paciente p) {
        if (p == null) return null;

        return PacienteEntity.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .edad(p.getEdad())
                .historialMedico(p.getHistorialMedico())
                .observacionesGenerales(p.getObservacionesGenerales())
                .imageUrl(p.getImageUrl())
                .assignedUserId(p.getAssignedUserId())
                .build();
    }
}
