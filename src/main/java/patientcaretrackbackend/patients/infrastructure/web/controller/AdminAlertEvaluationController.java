package patientcaretrackbackend.patients.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.usecase.AlertEvaluationUseCase;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/admin/alerts/evaluate")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminAlertEvaluationController {

    private final AlertEvaluationUseCase evaluationUseCase;

    @Operation(summary = "Evaluate NO_MEALS alerts for a given date (Europe/Madrid)")
    @PostMapping("/no-meals")
    public ResponseEntity<Map<String, Integer>> evaluateNoMeals(
            @Parameter(description = "Date in YYYY-MM-DD", example = "2025-12-31")
            @RequestParam("date") LocalDate date
    ) {
        int created = evaluationUseCase.evaluateNoMealsForDate(date);
        return ResponseEntity.ok(Map.of("created", created));
    }
}
