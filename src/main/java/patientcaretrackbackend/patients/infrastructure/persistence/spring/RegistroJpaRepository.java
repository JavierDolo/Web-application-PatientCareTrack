package patientcaretrackbackend.patients.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.RegistroEntity;

import java.util.List;

public interface RegistroJpaRepository extends JpaRepository<RegistroEntity, Long> {
    List<RegistroEntity> findByPacienteId(Long pacienteId);
}
