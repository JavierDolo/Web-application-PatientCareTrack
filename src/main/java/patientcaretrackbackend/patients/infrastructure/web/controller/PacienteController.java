package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.domain.model.Paciente;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@CrossOrigin
public class PacienteController {

    private final PacienteUseCase pacienteUseCase;

    @GetMapping
    public List<Paciente> all() {
        return pacienteUseCase.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> one(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteUseCase.get(id));
    }

    @PostMapping
    public Paciente create(@RequestBody Paciente p) {
        return pacienteUseCase.create(p);
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
}
