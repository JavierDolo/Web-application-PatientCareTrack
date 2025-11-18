package patientcaretrackbackend.infrastructure.persistence.jpa.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.UserEntity;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
