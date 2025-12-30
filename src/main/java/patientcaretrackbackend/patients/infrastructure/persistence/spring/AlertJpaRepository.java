package patientcaretrackbackend.patients.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.patients.domain.model.AlertStatus;
import patientcaretrackbackend.patients.domain.model.AlertType;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.AlertEntity;

import java.util.List;
import java.util.Optional;

public interface AlertJpaRepository extends JpaRepository<AlertEntity, Long> {

    Optional<AlertEntity> findByDedupeKey(String dedupeKey);

    List<AlertEntity> findByStatusOrderByCreatedAtDesc(AlertStatus status);

    List<AlertEntity> findByPacienteIdAndTypeAndStatus(Long pacienteId, AlertType type, AlertStatus status);
}
