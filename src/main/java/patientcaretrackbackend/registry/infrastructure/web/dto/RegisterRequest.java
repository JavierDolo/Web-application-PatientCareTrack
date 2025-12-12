package patientcaretrackbackend.registry.infrastructure.web.dto;

import patientcaretrackbackend.registry.domain.model.Role;

public record RegisterRequest(String username, String password, Role role) {}
