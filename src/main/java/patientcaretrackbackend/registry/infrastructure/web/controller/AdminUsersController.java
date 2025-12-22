package patientcaretrackbackend.registry.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import patientcaretrackbackend.registry.application.dto.UserSummaryDto;
import patientcaretrackbackend.registry.application.usecase.AdminUserUseCase;
import patientcaretrackbackend.registry.application.usecase.AuthUseCase;
import patientcaretrackbackend.registry.infrastructure.web.dto.RegisterRequest;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class AdminUsersController {

    private final AuthUseCase authUseCase;
    private final AdminUserUseCase adminUserUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RegisterRequest request) {
        authUseCase.register(request.username(), request.password(), request.role());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<UserSummaryDto> all() {
        return adminUserUseCase.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSummaryDto> one(@PathVariable("id") Long id) {
        return ResponseEntity.ok(adminUserUseCase.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        authUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
