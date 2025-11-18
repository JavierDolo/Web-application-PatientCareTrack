package patientcaretrackbackend.infrastructure.persistence.jpa.adapter;

import org.springframework.stereotype.Repository;
import patientcaretrackbackend.domain.model.Alert;
import patientcaretrackbackend.domain.port.AlertRepository;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.AlertEntity;
import patientcaretrackbackend.infrastructure.persistence.jpa.mapper.AlertMapper;
import patientcaretrackbackend.infrastructure.persistence.jpa.spring.AlertJpaRepository;

@Repository
public class AlertRepositoryAdapter
        extends AbstractJpaCrudAdapter<Alert, AlertEntity, Long, AlertJpaRepository>
        implements AlertRepository {

    public AlertRepositoryAdapter(AlertJpaRepository repo) {
        super(repo, new AlertMapper());
    }
}
