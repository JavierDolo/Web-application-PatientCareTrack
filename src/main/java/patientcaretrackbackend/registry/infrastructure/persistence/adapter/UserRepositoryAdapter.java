package patientcaretrackbackend.registry.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import patientcaretrackbackend.shared.persistence.AbstractJpaCrudAdapter;
import patientcaretrackbackend.registry.domain.model.User;
import patientcaretrackbackend.registry.domain.port.UserRepository;
import patientcaretrackbackend.registry.infrastructure.persistence.entity.UserEntity;
import patientcaretrackbackend.patients.infrastructure.persistence.mapper.mapper.UserMapper;
import patientcaretrackbackend.registry.infrastructure.persistence.spring.UserJpaRepository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter
        extends AbstractJpaCrudAdapter<User, UserEntity, Long, UserJpaRepository>
        implements UserRepository {

    public UserRepositoryAdapter(UserJpaRepository repo) {
        super(repo, new UserMapper());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username).map(mapper::toDomain);
    }
}
