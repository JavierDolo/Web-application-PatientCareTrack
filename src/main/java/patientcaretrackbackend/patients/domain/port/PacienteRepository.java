package patientcaretrackbackend.patients.domain.port;

import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.shared.persistence.CrudRepositoryPort;

import java.util.List;

public interface PacienteRepository extends CrudRepositoryPort<Paciente, Long> {
    List<Paciente> findByAssignedUserId(Long userId);
}
