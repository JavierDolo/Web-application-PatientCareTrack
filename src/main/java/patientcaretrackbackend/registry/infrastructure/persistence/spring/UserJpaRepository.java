package patientcaretrackbackend.registry.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.registry.infrastructure.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
