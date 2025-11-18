package patientcaretrackbackend.adapters.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.adapters.web.dto.AuthRequest;
import patientcaretrackbackend.adapters.web.dto.RegisterRequest;
import patientcaretrackbackend.application.usecase.AuthUseCase;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authUseCase.register(request.username(), request.password(), request.role());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        String token = authUseCase.login(request.username(), request.password());
        return ResponseEntity.ok(token);
    }
}
