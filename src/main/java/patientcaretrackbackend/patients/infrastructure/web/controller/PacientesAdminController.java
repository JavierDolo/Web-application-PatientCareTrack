package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.domain.model.Paciente;

@RestController
@RequestMapping("/admin/pacientes")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class PacientesAdminController {

    private final PacienteUseCase pacienteUseCase;

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody Paciente p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteUseCase.create(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente p) {
        return ResponseEntity.ok(pacienteUseCase.update(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/assign/{userId}")
    public ResponseEntity<Void> assign(@PathVariable Long id, @PathVariable Long userId) {
        pacienteUseCase.assignPaciente(id, userId);
        return ResponseEntity.noContent().build();
    }
}
