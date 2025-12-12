package patientcaretrackbackend.patients.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.PacienteEntity;

public interface PacienteJpaRepository extends JpaRepository<PacienteEntity, Long> {
}
