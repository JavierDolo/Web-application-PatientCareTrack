package patientcaretrackbackend.infrastructure.persistence.jpa.adapter;

import org.springframework.stereotype.Repository;
import patientcaretrackbackend.domain.model.User;
import patientcaretrackbackend.domain.port.UserRepository;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.UserEntity;
import patientcaretrackbackend.infrastructure.persistence.jpa.mapper.UserMapper;
import patientcaretrackbackend.infrastructure.persistence.jpa.spring.UserJpaRepository;

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
