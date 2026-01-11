package patientcaretrackbackend.patients.application.usecase;

import java.time.LocalDate;

public interface AlertEvaluationUseCase {
    int evaluateNoMealsForDate(LocalDate date);
}
