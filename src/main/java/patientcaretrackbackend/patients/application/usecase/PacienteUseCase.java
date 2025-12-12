package patientcaretrackbackend.patients.application.usecase;

import patientcaretrackbackend.patients.domain.model.Paciente;

import java.util.List;

public interface PacienteUseCase {
    List<Paciente> all();
    Paciente get(Long id);
    Paciente create(Paciente p);
    Paciente update(Long id, Paciente p);
    void delete(Long id);
}
