package patientcaretrackbackend.registry.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @NotBlank String newPassword
) {}
