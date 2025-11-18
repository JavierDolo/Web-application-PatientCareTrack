package patientcaretrackbackend.application.usecase;

import patientcaretrackbackend.domain.model.Alert;

import java.util.List;

public interface AlertUseCase {
    void resolver(Long id);
    List<Alert> all();
}
