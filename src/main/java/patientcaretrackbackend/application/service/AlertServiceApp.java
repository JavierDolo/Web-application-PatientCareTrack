package patientcaretrackbackend.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.application.usecase.AlertUseCase;
import patientcaretrackbackend.domain.model.Alert;
import patientcaretrackbackend.domain.port.AlertRepository;

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
