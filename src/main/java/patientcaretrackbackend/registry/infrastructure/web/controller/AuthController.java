package patientcaretrackbackend.registry.infrastructure.web.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.registry.infrastructure.web.dto.AuthRequest;
import patientcaretrackbackend.registry.application.usecase.AuthUseCase;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthUseCase authUseCase;

    @PermitAll
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        String token = authUseCase.login(request.username(), request.password());
        return ResponseEntity.ok(token);
    }
}
