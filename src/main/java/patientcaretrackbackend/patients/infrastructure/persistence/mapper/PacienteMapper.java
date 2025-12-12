package patientcaretrackbackend.patients.infrastructure.persistence.mapper;

import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.PacienteEntity;
import patientcaretrackbackend.shared.persistence.mapper.Mapper;

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
