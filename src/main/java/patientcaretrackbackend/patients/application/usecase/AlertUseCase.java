package patientcaretrackbackend.patients.application.usecase;

import patientcaretrackbackend.patients.domain.model.Alert;

import java.util.List;

public interface AlertUseCase {
    List<Alert> open();
    List<Alert> all();
    void resolve(Long alertId);
    long openCount();
}
