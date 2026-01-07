package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.usecase.AlertEvaluationUseCase;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/alerts/evaluate")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminAlertEvaluationController {

    private final AlertEvaluationUseCase evaluationUseCase;

    @PostMapping("/no-meals")
    public ResponseEntity<Void> evaluateNoMeals(
            @RequestParam("date") LocalDate date
    ) {
        evaluationUseCase.evaluateNoMealsForDate(date);
        return ResponseEntity.noContent().build();
    }
}
