package patientcaretrackbackend.registry.application.usecase;

import patientcaretrackbackend.registry.domain.model.Role;
import patientcaretrackbackend.registry.domain.model.User;

public interface AuthUseCase {
    void register(String username, String rawPassword, Role role);
    String login(String username, String rawPassword);
    User getByUsername(String username);
}
