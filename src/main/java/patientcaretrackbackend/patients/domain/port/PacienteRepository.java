package patientcaretrackbackend.patients.domain.port;

import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.shared.persistence.CrudRepositoryPort;

public interface PacienteRepository extends CrudRepositoryPort<Paciente, Long> {
}
