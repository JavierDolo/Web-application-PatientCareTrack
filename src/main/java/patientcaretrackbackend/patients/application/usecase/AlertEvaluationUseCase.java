package patientcaretrackbackend.patients.application.usecase;

import java.time.LocalDate;

public interface AlertEvaluationUseCase {
    void evaluateNoMealsForDate(LocalDate date);
}
