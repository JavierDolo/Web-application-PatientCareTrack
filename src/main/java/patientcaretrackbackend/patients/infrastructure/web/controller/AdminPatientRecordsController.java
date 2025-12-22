package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.usecase.RegistroUseCase;
import patientcaretrackbackend.patients.domain.model.Registro;

import java.util.List;

@RestController
@RequestMapping("/admin/patients/{patientId}/records")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminPatientRecordsController {

    private final RegistroUseCase registroUseCase;

    @GetMapping
    public List<Registro> all(@PathVariable("patientId") Long patientId) {
        return registroUseCase.findByPacienteId(patientId);
    }
}
