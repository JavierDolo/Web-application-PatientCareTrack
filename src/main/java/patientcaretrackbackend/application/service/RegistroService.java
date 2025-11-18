package patientcaretrackbackend.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.application.usecase.RegistroUseCase;
import patientcaretrackbackend.domain.model.Registro;
import patientcaretrackbackend.domain.port.RegistroRepository;

@Service
@RequiredArgsConstructor
public class RegistroService implements RegistroUseCase {

    private final RegistroRepository registroRepository;

    @Override
    public Registro crear(Registro registro) {
        return registroRepository.save(registro);
    }
}
