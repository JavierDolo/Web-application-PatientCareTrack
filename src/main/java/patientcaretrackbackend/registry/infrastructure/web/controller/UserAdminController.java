package patientcaretrackbackend.registry.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.registry.application.usecase.AuthUseCase;
import patientcaretrackbackend.registry.domain.model.Role;
import patientcaretrackbackend.registry.infrastructure.web.dto.RegisterRequest;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class UserAdminController {

    private final AuthUseCase authUseCase;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody RegisterRequest request) {
        authUseCase.register(
                request.username(),
                request.password(),
                request.role()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        authUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
