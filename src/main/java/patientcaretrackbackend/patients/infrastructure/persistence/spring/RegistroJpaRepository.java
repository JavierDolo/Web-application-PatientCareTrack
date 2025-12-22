package patientcaretrackbackend.patients.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.RegistroEntity;

import java.time.Instant;
import java.util.List;

public interface RegistroJpaRepository extends JpaRepository<RegistroEntity, Long> {
    List<RegistroEntity> findByPacienteId(Long pacienteId);
    List<RegistroEntity> findByPacienteIdAndCreatedAtBetween(
            Long pacienteId,
            Instant from,
            Instant to
    );

}
