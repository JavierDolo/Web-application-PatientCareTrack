package patientcaretrackbackend.patients.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patientcaretrackbackend.patients.domain.model.Registro;
import patientcaretrackbackend.patients.domain.port.RegistroRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.mapper.RegistroMapper;
import patientcaretrackbackend.patients.infrastructure.persistence.spring.RegistroJpaRepository;

import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RegistroRepositoryAdapter implements RegistroRepository {

    private final RegistroJpaRepository repo;
    private final RegistroMapper mapper;

    @Override
    public Registro save(Registro registro) {
        var saved = repo.save(mapper.toEntity(registro));
        return mapper.toDomain(saved);
    }

    @Override
    public List<Registro> findByPacienteId(Long pacienteId) {
        return mapper.toDomainList(repo.findByPacienteId(pacienteId));
    }

    @Override
    public List<Registro> findByPacienteIdAndBetween(
            Long pacienteId,
            Instant from,
            Instant to
    ) {
        return mapper.toDomainList(
                repo.findByPacienteIdAndCreatedAtBetween(pacienteId, from, to)
        );
    }

}
