package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.dto.PacienteDetailDto;
import patientcaretrackbackend.patients.application.dto.PacienteSummaryDto;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.infrastructure.web.mapper.PacienteDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/me/patients")
@RequiredArgsConstructor
@CrossOrigin
public class MyPatientsController {

    private final PacienteUseCase pacienteUseCase;
    private final PacienteDtoMapper dtoMapper;

    @GetMapping
    public List<PacienteSummaryDto> all(Authentication auth) {
        String username = auth.getName();
        return pacienteUseCase.allForUser(username).stream()
                .map(dtoMapper::toSummaryDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDetailDto> one(@PathVariable("id") Long id, Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(dtoMapper.toDetailDto(pacienteUseCase.getForUser(id, username)));
    }
}
