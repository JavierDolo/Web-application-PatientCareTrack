package patientcaretrackbackend.patients.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.domain.port.PacienteRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.mapper.PacienteMapper;
import patientcaretrackbackend.patients.infrastructure.persistence.spring.PacienteJpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PacienteRepositoryAdapter implements PacienteRepository {

    private final PacienteJpaRepository jpaRepository;
    private final PacienteMapper mapper;

    @Override
    public List<Paciente> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Paciente save(Paciente paciente) {

        // UPDATE (merge)
        if (paciente.getId() != null && jpaRepository.existsById(paciente.getId())) {
            var entity = jpaRepository.findById(paciente.getId())
                    .orElseThrow(() -> new RuntimeException("Paciente not found: " + paciente.getId()));

            mapper.updateEntity(entity, paciente);
            var saved = jpaRepository.save(entity);
            return mapper.toDomain(saved);
        }

        // CREATE
        var saved = jpaRepository.save(mapper.toEntity(paciente));
        return mapper.toDomain(saved);
    }


    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Paciente> findByAssignedUserId(Long userId) {
        return jpaRepository.findByAssignedUserId(userId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
