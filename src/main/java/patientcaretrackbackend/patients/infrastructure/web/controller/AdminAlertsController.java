package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.usecase.AlertUseCase;
import patientcaretrackbackend.patients.domain.model.Alert;

import java.util.List;

@RestController
@RequestMapping("/admin/alerts")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminAlertsController {

    private final AlertUseCase alertUseCase;

    @GetMapping
    public List<Alert> open() {
        return alertUseCase.open();
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<Void> resolve(@PathVariable("id") Long id) {
        alertUseCase.resolve(id);
        return ResponseEntity.noContent().build();
    }
}
