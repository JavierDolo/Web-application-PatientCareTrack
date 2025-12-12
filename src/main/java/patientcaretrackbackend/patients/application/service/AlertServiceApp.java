package patientcaretrackbackend.patients.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.patients.application.usecase.AlertUseCase;
import patientcaretrackbackend.patients.domain.model.Alert;
import patientcaretrackbackend.patients.domain.port.AlertRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertServiceApp implements AlertUseCase {

    private final AlertRepository alertRepository;

    @Override
    public void resolver(Long id) {
        alertRepository.findById(id).ifPresent(alert -> {
            alert.setResuelta(true);
            alertRepository.save(alert);
        });
    }

    @Override
    public List<Alert> all() {
        return alertRepository.findAll();
    }
}
