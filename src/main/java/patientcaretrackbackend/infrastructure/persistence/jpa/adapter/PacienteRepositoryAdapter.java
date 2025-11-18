package patientcaretrackbackend.infrastructure.persistence.jpa.adapter;

import org.springframework.stereotype.Repository;
import patientcaretrackbackend.domain.model.Paciente;
import patientcaretrackbackend.domain.port.PacienteRepository;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.PacienteEntity;
import patientcaretrackbackend.infrastructure.persistence.jpa.mapper.PacienteMapper;
import patientcaretrackbackend.infrastructure.persistence.jpa.spring.PacienteJpaRepository;

@Repository
public class PacienteRepositoryAdapter
        extends AbstractJpaCrudAdapter<Paciente, PacienteEntity, Long, PacienteJpaRepository>
        implements PacienteRepository {

    public PacienteRepositoryAdapter(PacienteJpaRepository repo) {
        super(repo, new PacienteMapper());
    }
}
