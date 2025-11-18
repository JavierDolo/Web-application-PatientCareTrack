package patientcaretrackbackend.infrastructure.persistence.jpa.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.AlertEntity;

public interface AlertJpaRepository extends JpaRepository<AlertEntity, Long> {
}
