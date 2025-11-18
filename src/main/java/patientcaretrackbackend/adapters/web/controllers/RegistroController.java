package patientcaretrackbackend.adapters.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.adapters.web.dto.RegistroRequest;
import patientcaretrackbackend.application.usecase.RegistroUseCase;
import patientcaretrackbackend.domain.model.Registro;

import java.time.Instant;

@RestController
@RequestMapping("/registros")
@RequiredArgsConstructor
@CrossOrigin
public class RegistroController {

    private final RegistroUseCase registroUseCase;

    @PostMapping
    public ResponseEntity<Registro> crear(@RequestBody RegistroRequest request, Authentication auth) {
        // TODO: en el futuro sacar userId real a partir de auth.getName()
        Long userId = 0L;

        Registro registro = buildRegistroFromRequest(request, userId);
        return ResponseEntity.ok(registroUseCase.crear(registro));
    }

    private Registro buildRegistroFromRequest(RegistroRequest r, Long userId) {
        return Registro.builder()
                .pacienteId(r.pacienteId())
                .creadoPorUserId(userId)
                .tipo(r.tipo())
                .comentario(r.comentario())
                .createdAt(Instant.now())
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
