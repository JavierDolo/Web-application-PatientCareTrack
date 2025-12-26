package patientcaretrackbackend.patients.application.usecase;

import patientcaretrackbackend.patients.application.dto.AlertDto;

import java.time.LocalDate;
import java.util.List;

public interface AlertUseCase {
    List<AlertDto> alertsForDate(LocalDate date);
}
