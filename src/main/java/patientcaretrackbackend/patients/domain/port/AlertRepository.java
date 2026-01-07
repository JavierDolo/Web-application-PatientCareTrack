package patientcaretrackbackend.patients.domain.port;

import patientcaretrackbackend.patients.domain.model.Alert;
import patientcaretrackbackend.patients.domain.model.AlertStatus;

import java.util.List;
import java.util.Optional;

public interface AlertRepository {
    Alert save(Alert alert);
    Optional<Alert> findById(Long id);
    Optional<Alert> findByDedupeKey(String key);

    boolean existsByDedupeKey(String key);

    List<Alert> findOpen();
    List<Alert> findByStatus(AlertStatus status);

    long countOpen();
}
