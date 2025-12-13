package patientcaretrackbackend.patients.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.PacienteEntity;

import java.util.List;

public interface PacienteJpaRepository extends JpaRepository<PacienteEntity, Long> {

    List<PacienteEntity> findByAssignedUserId(Long assignedUserId);
}
