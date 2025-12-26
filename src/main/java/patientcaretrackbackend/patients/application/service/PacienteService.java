package patientcaretrackbackend.patients.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.domain.port.PacienteRepository;
import patientcaretrackbackend.patients.infrastructure.web.dto.PacienteUpdateParcialRequest;
import patientcaretrackbackend.registry.domain.model.Role;
import patientcaretrackbackend.registry.domain.model.User;
import patientcaretrackbackend.registry.domain.port.UserRepository;
import patientcaretrackbackend.shared.exception.ForbiddenException;
import patientcaretrackbackend.shared.exception.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService implements PacienteUseCase {

    private final PacienteRepository pacienteRepository;
    private final UserRepository userRepository;

    // CRUD genérico (usado por admin)
    @Override
    public List<Paciente> all() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente get(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente no encontrado: " + id));
    }

    @Override
    public Paciente create(Paciente p) {
        return pacienteRepository.save(p);
    }

    @Override
    public Paciente update(Long id, Paciente p) {
        Paciente existing = get(id);
        p.setId(existing.getId());
        // Conservamos assignedUserId si no viene en el request
        if (p.getAssignedUserId() == null) {
            p.setAssignedUserId(existing.getAssignedUserId());
        }
        return pacienteRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        // fuerza 404 si no existe
        get(id);
        pacienteRepository.deleteById(id);
    }

    // Métodos conscientes del usuario logado

    @Override
    public List<Paciente> allForUser(String username) {
        User user = getUserByUsername(username);
        if (user.getRole() == Role.ADMIN) {
            return pacienteRepository.findAll();
        }
        return pacienteRepository.findByAssignedUserId(user.getId());
    }

    @Override
    public Paciente getForUser(Long id, String username) {
        User user = getUserByUsername(username);
        Paciente paciente = get(id);

        if (user.getRole() == Role.ADMIN) {
            return paciente;
        }

        if (paciente.getAssignedUserId() == null || !paciente.getAssignedUserId().equals(user.getId())) {
            throw new ForbiddenException("No tienes permisos para acceder a este paciente");
        }

        return paciente;
    }

    @Override
    public void assignPaciente(Long pacienteId, Long userId) {
        Paciente paciente = get(pacienteId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + userId));

        paciente.setAssignedUserId(user.getId());
        pacienteRepository.save(paciente);
    }

    @Override
    public Paciente updatePartial(Long id, PacienteUpdateParcialRequest req, String username) {
        User user = getUserByUsername(username);
        Paciente paciente = getForUser(id, username); // ya valida pertenencia

        // Aquí lo profesional sería crear un Registro diario, pero de momento
        // asumimos que el paciente tiene campos para estado actual.
        // Puedes adaptar esto si decides modelarlo con Registro.

        // TODO: mapear campos del request a los campos adecuados
        // ahora mismo solo devolvemos el mismo paciente sin cambios
        return paciente;
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + username));
    }
}
