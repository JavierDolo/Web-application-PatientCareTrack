package patientcaretrackbackend.patients.domain.port;

import patientcaretrackbackend.patients.domain.model.Alert;
import patientcaretrackbackend.shared.persistence.CrudRepositoryPort;

public interface AlertRepository extends CrudRepositoryPort<Alert, Long> {
}
