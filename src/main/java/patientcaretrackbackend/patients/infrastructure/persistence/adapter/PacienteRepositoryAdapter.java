package patientcaretrackbackend.patients.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.domain.port.PacienteRepository;
import patientcaretrackbackend.shared.persistence.AbstractJpaCrudAdapter;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.PacienteEntity;
import patientcaretrackbackend.patients.infrastructure.persistence.mapper.PacienteMapper;
import patientcaretrackbackend.patients.infrastructure.persistence.spring.PacienteJpaRepository;

@Repository
public class PacienteRepositoryAdapter
        extends AbstractJpaCrudAdapter<Paciente, PacienteEntity, Long, PacienteJpaRepository>
        implements PacienteRepository {

    public PacienteRepositoryAdapter(PacienteJpaRepository repo) {
        super(repo, new PacienteMapper());
    }
}
