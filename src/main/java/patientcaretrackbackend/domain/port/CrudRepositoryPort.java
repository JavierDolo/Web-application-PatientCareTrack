package patientcaretrackbackend.domain.port;

import java.util.List;
import java.util.Optional;

public interface CrudRepositoryPort<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T aggregate);
    void deleteById(ID id);
    boolean existsById(ID id);
}
