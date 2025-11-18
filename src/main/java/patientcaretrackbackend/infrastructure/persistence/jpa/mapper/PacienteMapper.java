package patientcaretrackbackend.infrastructure.persistence.jpa.mapper;

import patientcaretrackbackend.domain.model.Paciente;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.PacienteEntity;

public class PacienteMapper implements Mapper<Paciente, PacienteEntity> {

    @Override
    public Paciente toDomain(PacienteEntity e) {
        if (e == null) return null;
        return Paciente.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .edad(e.getEdad())
                .datosMedicos(e.getDatosMedicos())
                .build();
    }

    @Override
    public PacienteEntity toEntity(Paciente d) {
        if (d == null) return null;
        return PacienteEntity.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .edad(d.getEdad())
                .datosMedicos(d.getDatosMedicos())
                .build();
    }
}
