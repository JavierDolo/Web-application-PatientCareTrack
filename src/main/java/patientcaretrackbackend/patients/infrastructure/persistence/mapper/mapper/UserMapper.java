package patientcaretrackbackend.patients.infrastructure.persistence.mapper.mapper;

import patientcaretrackbackend.registry.domain.model.User;
import patientcaretrackbackend.registry.infrastructure.persistence.entity.UserEntity;
import patientcaretrackbackend.shared.persistence.mapper.Mapper;

public class UserMapper implements Mapper<User, UserEntity> {

    @Override
    public User toDomain(UserEntity e) {
        if (e == null) return null;
        return User.builder()
                .id(e.getId())
                .username(e.getUsername())
                .passwordHash(e.getPasswordHash())
                .role(e.getRole())
                .pacientesIds(e.getPacientesIds())
                .build();
    }

    @Override
    public UserEntity toEntity(User d) {
        if (d == null) return null;
        return UserEntity.builder()
                .id(d.getId())
                .username(d.getUsername())
                .passwordHash(d.getPasswordHash())
                .role(d.getRole())
                .pacientesIds(d.getPacientesIds())
                .build();
    }
}
