package patientcaretrackbackend.patients.domain.port;

import patientcaretrackbackend.patients.domain.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository {
    List<Paciente> findAll();
    Optional<Paciente> findById(Long id);
    Paciente save(Paciente paciente);
    void deleteById(Long id);

    List<Paciente> findByAssignedUserId(Long userId);
}
