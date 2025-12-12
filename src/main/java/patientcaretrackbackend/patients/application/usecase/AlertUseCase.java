package patientcaretrackbackend.patients.application.usecase;

import patientcaretrackbackend.patients.domain.model.Alert;

import java.util.List;

public interface AlertUseCase {
    void resolver(Long id);
    List<Alert> all();
}
