package patientcaretrackbackend.registry.application.usecase;

import patientcaretrackbackend.registry.domain.model.Role;

public interface AuthUseCase {

    void register(String username, String rawPassword, Role role);

    String login(String username, String rawPassword);

    void deleteUser(Long userId);
}
