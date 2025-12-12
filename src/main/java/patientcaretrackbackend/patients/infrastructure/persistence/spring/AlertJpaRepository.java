package patientcaretrackbackend.patients.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.AlertEntity;

public interface AlertJpaRepository extends JpaRepository<AlertEntity, Long> {
}
