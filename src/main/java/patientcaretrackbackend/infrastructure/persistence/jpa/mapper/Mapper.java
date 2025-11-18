package patientcaretrackbackend.infrastructure.persistence.jpa.mapper;

import java.util.List;

public interface Mapper<D, E> {
    D toDomain(E entity);
    E toEntity(D domain);

    default List<D> toDomainList(List<E> entities) {
        return entities.stream().map(this::toDomain).toList();
    }

    default List<E> toEntityList(List<D> domains) {
        return domains.stream().map(this::toEntity).toList();
    }
}
