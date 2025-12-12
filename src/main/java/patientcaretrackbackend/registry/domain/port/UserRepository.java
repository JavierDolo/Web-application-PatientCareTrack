package patientcaretrackbackend.registry.domain.port;

import patientcaretrackbackend.shared.persistence.CrudRepositoryPort;
import patientcaretrackbackend.registry.domain.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepositoryPort<User, Long> {
    Optional<User> findByUsername(String username);
}
