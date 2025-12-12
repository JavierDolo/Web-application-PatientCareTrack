package patientcaretrackbackend.registry.infrastructure.web.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.registry.infrastructure.web.dto.AuthRequest;
import patientcaretrackbackend.registry.infrastructure.web.dto.RegisterRequest;
import patientcaretrackbackend.registry.application.usecase.AuthUseCase;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthUseCase authUseCase;

    @PermitAll
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        authUseCase.register(
                request.username(),
                request.password(),
                request.role()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PermitAll
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        String token = authUseCase.login(request.username(), request.password());
        return ResponseEntity.ok(token);
    }
}
