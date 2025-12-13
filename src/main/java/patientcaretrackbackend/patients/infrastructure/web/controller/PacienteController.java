package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.infrastructure.web.dto.PacienteUpdateParcialRequest;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@CrossOrigin
public class PacienteController {

    private final PacienteUseCase pacienteUseCase;

    @GetMapping
    public List<Paciente> list(Principal principal) {
        return pacienteUseCase.allForUser(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> one(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(pacienteUseCase.getForUser(id, principal.getName()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> updatePartial(
            @PathVariable Long id,
            @RequestBody PacienteUpdateParcialRequest req,
            Principal principal
    ) {
        return ResponseEntity.ok(pacienteUseCase.updatePartial(id, req, principal.getName()));
    }
}
