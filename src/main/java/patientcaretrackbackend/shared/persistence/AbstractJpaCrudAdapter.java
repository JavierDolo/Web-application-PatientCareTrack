package patientcaretrackbackend.shared.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import patientcaretrackbackend.shared.persistence.mapper.Mapper;

import java.util.List;
import java.util.Optional;

public abstract class AbstractJpaCrudAdapter<D, E, ID, R extends JpaRepository<E, ID>>
        implements CrudRepositoryPort<D, ID> {

    protected final R repo;
    protected final Mapper<D, E> mapper;

    protected AbstractJpaCrudAdapter(R repo, Mapper<D, E> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<D> findAll() {
        return mapper.toDomainList(repo.findAll());
    }

    @Override
    public Optional<D> findById(ID id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public D save(D aggregate) {
        E entity = mapper.toEntity(aggregate);
        E saved = repo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repo.existsById(id);
    }
}
