package patientcaretrackbackend.patients.application.usecase;

import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.infrastructure.web.dto.PacienteUpdateParcialRequest;

import java.util.List;

public interface PacienteUseCase {

    // CRUD genérico (para admin)
    List<Paciente> all();
    Paciente get(Long id);
    Paciente create(Paciente p);
    Paciente update(Long id, Paciente p);
    void delete(Long id);

    // Para seguridad / usuario logado
    List<Paciente> allForUser(String username);
    Paciente getForUser(Long id, String username);

    // Asignar pacientes
    void assignPaciente(Long pacienteId, Long userId);

    // Update parcial para USER (comidas, deposiciones, etc.)
    Paciente updatePartial(Long id, PacienteUpdateParcialRequest req, String username);
}
