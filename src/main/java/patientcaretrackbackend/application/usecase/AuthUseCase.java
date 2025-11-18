package patientcaretrackbackend.application.usecase;

import patientcaretrackbackend.domain.model.Role;
import patientcaretrackbackend.domain.model.User;

public interface AuthUseCase {
    void register(String username, String rawPassword, Role role);
    String login(String username, String rawPassword);
    User getByUsername(String username);
}
