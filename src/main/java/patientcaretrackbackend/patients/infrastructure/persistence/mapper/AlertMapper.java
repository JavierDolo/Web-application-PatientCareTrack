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
                .type(e.getType())
                .status(e.getStatus())
                .pacienteId(e.getPacienteId())
                .createdByUserId(e.getCreatedByUserId())
                .message(e.getMessage())
                .forDate(e.getForDate())
                .dedupeKey(e.getDedupeKey())
                .createdAt(e.getCreatedAt())
                .resolvedAt(e.getResolvedAt())
                .build();
    }

    @Override
    public AlertEntity toEntity(Alert d) {
        if (d == null) return null;
        return AlertEntity.builder()
                .id(d.getId())
                .type(d.getType())
                .status(d.getStatus())
                .pacienteId(d.getPacienteId())
                .createdByUserId(d.getCreatedByUserId())
                .message(d.getMessage())
                .forDate(d.getForDate())
                .dedupeKey(d.getDedupeKey())
                .createdAt(d.getCreatedAt())
                .resolvedAt(d.getResolvedAt())
                .build();
    }
}
