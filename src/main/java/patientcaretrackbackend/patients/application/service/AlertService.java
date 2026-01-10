package patientcaretrackbackend.patients.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.patients.application.usecase.AlertUseCase;
import patientcaretrackbackend.patients.domain.model.Alert;
import patientcaretrackbackend.patients.domain.model.AlertStatus;
import patientcaretrackbackend.patients.domain.port.AlertRepository;
import patientcaretrackbackend.shared.exception.NotFoundException;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService implements AlertUseCase {

    private final AlertRepository alertRepository;

    @Override
    public List<Alert> open() {
        return alertRepository.findOpen();
    }

    @Override
    public void resolve(Long alertId) {
        var alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new NotFoundException("Alert not found: " + alertId));

        if (alert.getStatus() == AlertStatus.RESOLVED) return;

        alert.setStatus(AlertStatus.RESOLVED);
        alert.setResolvedAt(Instant.now());
        alertRepository.save(alert);
    }
    @Override
    public long openCount() {
        return alertRepository.countOpen();
    }
    @Override
    public List<Alert> all() {
        return alertRepository.findAllSorted();
    }


}
