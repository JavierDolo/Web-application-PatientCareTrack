package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.dto.PacienteDetailDto;
import patientcaretrackbackend.patients.application.dto.PacienteSummaryDto;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.infrastructure.web.dto.PacienteUpdateParcialRequest;
import patientcaretrackbackend.patients.infrastructure.web.mapper.PacienteDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@CrossOrigin
public class PacienteController {

    private final PacienteUseCase pacienteUseCase;
    private final PacienteDtoMapper dtoMapper;

    // USER/ADMIN: lista solo los asignados al usuario logado
    @GetMapping
    public List<PacienteSummaryDto> all(Authentication auth) {
        String username = auth.getName();
        return pacienteUseCase.allForUser(username).stream()
                .map(dtoMapper::toSummaryDto)
                .toList();
    }

    // USER/ADMIN: detalle solo si está asignado
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDetailDto> one(@PathVariable Long id, Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(dtoMapper.toDetailDto(pacienteUseCase.getForUser(id, username)));
    }

    // USER: update parcial (comidas, higiene, notas, etc.)
    @PatchMapping("/{id}")
    public ResponseEntity<PacienteDetailDto> updatePartial(
            @PathVariable Long id,
            @RequestBody PacienteUpdateParcialRequest req,
            Authentication auth
    ) {
        String username = auth.getName();
        return ResponseEntity.ok(dtoMapper.toDetailDto(pacienteUseCase.updatePartial(id, req, username)));
    }
}
