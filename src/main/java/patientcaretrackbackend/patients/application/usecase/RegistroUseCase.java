package patientcaretrackbackend.patients.application.usecase;

import patientcaretrackbackend.patients.domain.model.Registro;

import java.time.Instant;
import java.util.List;

public interface RegistroUseCase {
    Registro crear(Registro registro);

    // NUEVO: admin/listado
    List<Registro> findByPacienteId(Long pacienteId);
    List<Registro> findByPacienteIdAndBetween(
            Long pacienteId,
            Instant from,
            Instant to
    );

}
