package patientcaretrackbackend.domain.port;

import patientcaretrackbackend.domain.model.Registro;

import java.util.List;

public interface RegistroRepository {
    Registro save(Registro registro);
    List<Registro> findByPacienteId(Long pacienteId);
}
