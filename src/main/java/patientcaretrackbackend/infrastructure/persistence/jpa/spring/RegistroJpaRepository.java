package patientcaretrackbackend.infrastructure.persistence.jpa.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.RegistroEntity;

import java.util.List;

public interface RegistroJpaRepository extends JpaRepository<RegistroEntity, Long> {
    List<RegistroEntity> findByPacienteId(Long pacienteId);
}
