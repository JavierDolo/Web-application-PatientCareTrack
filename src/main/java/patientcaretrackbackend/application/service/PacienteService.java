package patientcaretrackbackend.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.application.usecase.PacienteUseCase;
import patientcaretrackbackend.domain.model.Paciente;
import patientcaretrackbackend.domain.port.PacienteRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService implements PacienteUseCase {

    private final PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> all() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente get(Long id) {
        return pacienteRepository.findById(id).orElseThrow();
    }

    @Override
    public Paciente create(Paciente p) {
        return pacienteRepository.save(p);
    }

    @Override
    public Paciente update(Long id, Paciente p) {
        Paciente existing = pacienteRepository.findById(id).orElseThrow();
        existing.setNombre(p.getNombre());
        existing.setEdad(p.getEdad());
        existing.setDatosMedicos(p.getDatosMedicos());
        return pacienteRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente not found");
        }
        pacienteRepository.deleteById(id);
    }
}
