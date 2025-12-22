package patientcaretrackbackend.patients.infrastructure.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.application.usecase.RegistroUseCase;
import patientcaretrackbackend.patients.domain.model.Registro;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;
import patientcaretrackbackend.patients.infrastructure.web.dto.records.RegistroAseoRequest;
import patientcaretrackbackend.patients.infrastructure.web.dto.records.RegistroBaseRequest;
import patientcaretrackbackend.patients.infrastructure.web.dto.records.RegistroComidaRequest;
import patientcaretrackbackend.patients.infrastructure.web.dto.records.RegistroDeposicionRequest;
import patientcaretrackbackend.patients.infrastructure.web.dto.records.RegistroLiquidosRequest;
import patientcaretrackbackend.registry.domain.port.UserRepository;

import java.time.Instant;

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
            @Valid @RequestBody RegistroBaseRequest request,
            Authentication auth
    ) {
        String username = auth.getName();

        // 1) valida que el paciente es del user
        pacienteUseCase.getForUser(patientId, username);

        // 2) userId real
        Long userId = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username))
                .getId();

        // 3) construir registro
        Registro registro = buildRegistroFromRequest(patientId, request, userId);

        return ResponseEntity.ok(registroUseCase.crear(registro));
    }

    private Registro buildRegistroFromRequest(Long patientId, RegistroBaseRequest r, Long userId) {

        Registro.RegistroBuilder b = Registro.builder()
                .pacienteId(patientId)
                .creadoPorUserId(userId)
                .tipo(r.tipo())
                .comentario(r.comentario())
                .notificarIncidencia(r.notificarIncidencia())
                .createdAt(Instant.now());

        if (r instanceof RegistroComidaRequest comida) {
            if (comida.tipo() == TipoRegistro.DESAYUNO) b.hizoDesayuno(comida.realizado());
            if (comida.tipo() == TipoRegistro.COMIDA)   b.hizoComida(comida.realizado());
            if (comida.tipo() == TipoRegistro.CENA)     b.hizoCena(comida.realizado());
        } else if (r instanceof RegistroDeposicionRequest dep) {
            b.hizoDeposicion(dep.hizoDeposicion());
            b.deposicionCalidad(dep.deposicionCalidad());
        } else if (r instanceof RegistroLiquidosRequest liq) {
            b.liquidosMl(liq.liquidosMl());
        } else if (r instanceof RegistroAseoRequest aseo) {
            b.aseoTipo(aseo.aseoTipo());
        }

        return b.build();
    }
}
