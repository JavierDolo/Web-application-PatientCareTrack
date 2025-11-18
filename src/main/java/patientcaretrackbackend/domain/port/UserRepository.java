package patientcaretrackbackend.domain.port;

import patientcaretrackbackend.domain.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepositoryPort<User, Long> {
    Optional<User> findByUsername(String username);
}
