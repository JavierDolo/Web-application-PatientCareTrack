package patientcaretrackbackend.registry.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.patients.application.usecase.RegistroUseCase;
import patientcaretrackbackend.patients.domain.model.Registro;
import patientcaretrackbackend.patients.domain.port.RegistroRepository;

@Service
@RequiredArgsConstructor
public class RegistroService implements RegistroUseCase {

    private final RegistroRepository registroRepository;

    @Override
    public Registro crear(Registro registro) {
        return registroRepository.save(registro);
    }
}
