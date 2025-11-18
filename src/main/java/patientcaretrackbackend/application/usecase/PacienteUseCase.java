package patientcaretrackbackend.application.usecase;

import patientcaretrackbackend.domain.model.Paciente;

import java.util.List;

public interface PacienteUseCase {
    List<Paciente> all();
    Paciente get(Long id);
    Paciente create(Paciente p);
    Paciente update(Long id, Paciente p);
    void delete(Long id);
}
