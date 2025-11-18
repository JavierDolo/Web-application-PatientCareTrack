package patientcaretrackbackend.infrastructure.persistence.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patientcaretrackbackend.domain.model.Registro;
import patientcaretrackbackend.domain.port.RegistroRepository;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.RegistroEntity;
import patientcaretrackbackend.infrastructure.persistence.jpa.mapper.RegistroMapper;
import patientcaretrackbackend.infrastructure.persistence.jpa.spring.RegistroJpaRepository;

import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RegistroRepositoryAdapter implements RegistroRepository {

    private final RegistroJpaRepository repo;
    private final RegistroMapper mapper = new RegistroMapper();

    @Override
    public Registro save(Registro registro) {
        if (registro.getCreatedAt() == null) {
            registro.setCreatedAt(Instant.now());
        }
        RegistroEntity entity = mapper.toEntity(registro);
        RegistroEntity saved = repo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Registro> findByPacienteId(Long pacienteId) {
        return mapper.toDomainList(repo.findByPacienteId(pacienteId));
    }
}
