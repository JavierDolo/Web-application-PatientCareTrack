package patientcaretrackbackend.patients.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patientcaretrackbackend.patients.domain.model.Alert;
import patientcaretrackbackend.patients.domain.model.AlertStatus;
import patientcaretrackbackend.patients.domain.port.AlertRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.mapper.AlertMapper;
import patientcaretrackbackend.patients.infrastructure.persistence.spring.AlertJpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlertRepositoryAdapter implements AlertRepository {

    private final AlertJpaRepository repo;
    private final AlertMapper mapper = new AlertMapper();

    @Override
    public Alert save(Alert alert) {
        return mapper.toDomain(repo.save(mapper.toEntity(alert)));
    }

    @Override
    public Optional<Alert> findById(Long id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Alert> findByDedupeKey(String key) {
        return repo.findByDedupeKey(key).map(mapper::toDomain);
    }

    @Override
    public List<Alert> findOpen() {
        return repo.findByStatusOrderByCreatedAtDesc(AlertStatus.OPEN)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Alert> findByStatus(AlertStatus status) {
        return repo.findByStatusOrderByCreatedAtDesc(status)
                .stream().map(mapper::toDomain).toList();
    }
    @Override
    public boolean existsByDedupeKey(String key) {
        return repo.existsByDedupeKey(key);
    }

    @Override
    public long countOpen() {
        return repo.countByStatus(AlertStatus.OPEN);
    }
    @Override
    public List<Alert> findAllSorted() {
        return repo.findAllByOrderByCreatedAtDesc()
                .stream().map(mapper::toDomain).toList();
    }


}
