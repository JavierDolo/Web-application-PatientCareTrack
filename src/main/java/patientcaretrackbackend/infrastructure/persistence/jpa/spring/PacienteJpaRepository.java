package patientcaretrackbackend.infrastructure.persistence.jpa.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.PacienteEntity;

public interface PacienteJpaRepository extends JpaRepository<PacienteEntity, Long> {
}
