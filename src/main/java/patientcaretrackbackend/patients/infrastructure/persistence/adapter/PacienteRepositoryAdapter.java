package patientcaretrackbackend.patients.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.domain.port.PacienteRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.PacienteEntity;
import patientcaretrackbackend.patients.infrastructure.persistence.mapper.PacienteMapper;
import patientcaretrackbackend.patients.infrastructure.persistence.spring.PacienteJpaRepository;
import patientcaretrackbackend.shared.persistence.AbstractJpaCrudAdapter;

import java.util.List;

@Repository
public class PacienteRepositoryAdapter
        extends AbstractJpaCrudAdapter<Paciente, PacienteEntity, Long, PacienteJpaRepository>
        implements PacienteRepository {

    public PacienteRepositoryAdapter(PacienteJpaRepository repo, PacienteMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public List<Paciente> findByAssignedUserId(Long userId) {
        return mapper.toDomainList(repo.findByAssignedUserId(userId));
    }
}
