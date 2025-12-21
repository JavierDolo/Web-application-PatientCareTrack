package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.dto.PacienteDetailDto;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.infrastructure.web.dto.PacienteCreateRequest;
import patientcaretrackbackend.patients.infrastructure.web.dto.PacienteUpdateRequest;
import patientcaretrackbackend.patients.infrastructure.web.mapper.PacienteDtoMapper;

@RestController
@RequestMapping("/admin/pacientes")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class PacientesAdminController {

    private final PacienteUseCase pacienteUseCase;
    private final PacienteDtoMapper dtoMapper;

    @PostMapping
    public ResponseEntity<PacienteDetailDto> create(@RequestBody PacienteCreateRequest req) {
        var created = pacienteUseCase.create(dtoMapper.fromCreateRequest(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toDetailDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDetailDto> update(@PathVariable Long id, @RequestBody PacienteUpdateRequest req) {
        var updated = pacienteUseCase.update(id, dtoMapper.fromUpdateRequest(id, req));
        return ResponseEntity.ok(dtoMapper.toDetailDto(updated));
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
