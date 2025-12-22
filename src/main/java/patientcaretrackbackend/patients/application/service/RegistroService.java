package patientcaretrackbackend.patients.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.patients.application.usecase.RegistroUseCase;
import patientcaretrackbackend.patients.domain.model.Registro;
import patientcaretrackbackend.patients.domain.port.RegistroRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroService implements RegistroUseCase {

    private final RegistroRepository registroRepository;

    @Override
    public Registro crear(Registro registro) {
        if (registro.getCreatedAt() == null) {
            registro.setCreatedAt(Instant.now());
        }
        return registroRepository.save(registro);
    }

    @Override
    public List<Registro> findByPacienteId(Long pacienteId) {
        return registroRepository.findByPacienteId(pacienteId);
    }
}
