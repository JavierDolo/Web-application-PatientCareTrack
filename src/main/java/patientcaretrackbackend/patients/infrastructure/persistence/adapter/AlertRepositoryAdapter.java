package patientcaretrackbackend.patients.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import patientcaretrackbackend.patients.domain.model.Alert;
import patientcaretrackbackend.patients.domain.port.AlertRepository;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.AlertEntity;
import patientcaretrackbackend.patients.infrastructure.persistence.mapper.AlertMapper;
import patientcaretrackbackend.patients.infrastructure.persistence.spring.AlertJpaRepository;
import patientcaretrackbackend.shared.persistence.AbstractJpaCrudAdapter;

@Repository
public class AlertRepositoryAdapter
        extends AbstractJpaCrudAdapter<Alert, AlertEntity, Long, AlertJpaRepository>
        implements AlertRepository {

    public AlertRepositoryAdapter(AlertJpaRepository repo) {
        super(repo, new AlertMapper());
    }
}
