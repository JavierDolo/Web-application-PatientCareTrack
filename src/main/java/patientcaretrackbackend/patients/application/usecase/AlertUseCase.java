package patientcaretrackbackend.patients.application.usecase;

import patientcaretrackbackend.patients.domain.model.Alert;

import java.util.List;

public interface AlertUseCase {
    List<Alert> open();
    void resolve(Long alertId);
    long openCount();
}
