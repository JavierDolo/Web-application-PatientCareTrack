package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.dto.AlertDto;
import patientcaretrackbackend.patients.application.usecase.AlertUseCase;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/alerts")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminAlertsController {

    private final AlertUseCase alertUseCase;

    @GetMapping
    public List<AlertDto> alerts(@RequestParam(required = false) String date) {
        LocalDate d = (date == null) ? LocalDate.now() : LocalDate.parse(date);
        return alertUseCase.alertsForDate(d);
    }
}
