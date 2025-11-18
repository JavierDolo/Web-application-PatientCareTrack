package patientcaretrackbackend.adapters.web.dto;

import patientcaretrackbackend.domain.model.Role;

public record RegisterRequest(String username, String password, Role role) {}
