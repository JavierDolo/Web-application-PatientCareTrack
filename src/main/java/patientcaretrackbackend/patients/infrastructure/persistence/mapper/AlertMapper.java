package patientcaretrackbackend.patients.infrastructure.persistence.mapper;

import patientcaretrackbackend.patients.domain.model.Alert;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.AlertEntity;
import patientcaretrackbackend.shared.persistence.mapper.Mapper;

public class AlertMapper implements Mapper<Alert, AlertEntity> {

    @Override
    public Alert toDomain(AlertEntity e) {
        if (e == null) return null;
        return Alert.builder()
                .id(e.getId())
                .pacienteId(e.getPacienteId())
                .generadoPorUserId(e.getGeneradoPorUserId())
                .mensaje(e.getMensaje())
                .resuelta(e.isResuelta())
                .createdAt(e.getCreatedAt())
                .build();
    }

    @Override
    public AlertEntity toEntity(Alert d) {
        if (d == null) return null;
        return AlertEntity.builder()
                .id(d.getId())
                .pacienteId(d.getPacienteId())
                .generadoPorUserId(d.getGeneradoPorUserId())
                .mensaje(d.getMensaje())
                .resuelta(d.isResuelta())
                .createdAt(d.getCreatedAt())
                .build();
    }
}
