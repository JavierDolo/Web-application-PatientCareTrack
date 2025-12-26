package patientcaretrackbackend.patients.domain.port;

import patientcaretrackbackend.patients.domain.model.Registro;

import java.time.Instant;
import java.util.List;

public interface RegistroRepository {
    Registro save(Registro registro);

    List<Registro> findByPacienteId(Long pacienteId);

    List<Registro> findByPacienteIdAndCreatedAtBetween(
            Long pacienteId,
            Instant from,
            Instant to
    );
}
