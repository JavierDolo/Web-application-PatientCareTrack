package patientcaretrackbackend.shared.persistence.mapper;

import java.util.List;

public interface Mapper<D, E> {
    D toDomain(E entity);
    E toEntity(D domain);

    /** Para updates: copiar campos del domain a una entity existente (JPA managed) */
    default void updateEntity(E entity, D domain) {}

    default List<D> toDomainList(List<E> entities) {
        return entities.stream().map(this::toDomain).toList();
    }

    default List<E> toEntityList(List<D> domains) {
        return domains.stream().map(this::toEntity).toList();
    }
}
