package patientcaretrackbackend.patients.application.usecase;

import patientcaretrackbackend.patients.domain.model.Registro;

import java.util.List;

public interface RegistroUseCase {
    Registro crear(Registro registro);

    // NUEVO: admin/listado
    List<Registro> findByPacienteId(Long pacienteId);
}
