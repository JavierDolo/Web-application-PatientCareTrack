package patientcaretrackbackend.patients.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.application.usecase.RegistroUseCase;
import patientcaretrackbackend.patients.domain.model.Registro;
import patientcaretrackbackend.patients.infrastructure.web.dto.RegistroRequest;
import patientcaretrackbackend.registry.domain.port.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/me/patients/{patientId}/records")
@RequiredArgsConstructor
@CrossOrigin
public class MyRecordsController {

    private final RegistroUseCase registroUseCase;
    private final PacienteUseCase pacienteUseCase;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Registro> create(
            @PathVariable("patientId") Long patientId,
            @RequestBody RegistroRequest request,
            Authentication auth
    ) {
        String username = auth.getName();

        // 1) valida que el paciente es del user
        pacienteUseCase.getForUser(patientId, username);

        // 2) userId real
        Long userId = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username))
                .getId();

        // 3) construir registro (sin createdAt)
        Registro registro = buildRegistroFromRequest(patientId, request, userId);

        return ResponseEntity.ok(registroUseCase.crear(registro));
    }

    @GetMapping
    public ResponseEntity<List<Registro>> list(
            @PathVariable("patientId") Long patientId,
            Authentication auth
    ) {
        String username = auth.getName();

        // valida que el paciente es del user
        pacienteUseCase.getForUser(patientId, username);

        return ResponseEntity.ok(registroUseCase.findByPacienteId(patientId));
    }

    private Registro buildRegistroFromRequest(Long patientId, RegistroRequest r, Long userId) {
        return Registro.builder()
                .pacienteId(patientId)
                .creadoPorUserId(userId)
                .tipo(r.tipo())
                .comentario(r.comentario())

                .hizoDeposicion(r.hizoDeposicion())
                .deposicionCalidad(r.deposicionCalidad())

                .hizoDesayuno(r.hizoDesayuno())
                .hizoComida(r.hizoComida())
                .hizoCena(r.hizoCena())

                .liquidosMl(r.liquidosMl())
                .aseoTipo(r.aseoTipo())

                .notificarIncidencia(r.notificarIncidencia())
                .build();
    }
}
