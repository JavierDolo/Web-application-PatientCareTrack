package patientcaretrackbackend.patients.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.patients.application.usecase.RegistroUseCase;
import patientcaretrackbackend.patients.domain.model.Alert;
import patientcaretrackbackend.patients.domain.model.AlertStatus;
import patientcaretrackbackend.patients.domain.model.AlertType;
import patientcaretrackbackend.patients.domain.model.Registro;
import patientcaretrackbackend.patients.domain.port.AlertRepository;
import patientcaretrackbackend.patients.domain.port.RegistroRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroService implements RegistroUseCase {

    private final RegistroRepository registroRepository;
    private final AlertRepository alertRepository; // 👈 AÑADIR

    @Override
    public Registro crear(Registro registro) {
        if (registro.getCreatedAt() == null) {
            registro.setCreatedAt(Instant.now());
        }

        // 1) guardamos el registro
        Registro saved = registroRepository.save(registro);

        // 2) si el usuario marcó incidencia => creamos alerta
        if (Boolean.TRUE.equals(saved.getNotificarIncidencia())) {
            createIncidenciaAlert(saved);
        }

        return saved;
    }

    @Override
    public List<Registro> findByPacienteId(Long pacienteId) {
        return registroRepository.findByPacienteId(pacienteId);
    }

    @Override
    public List<Registro> findByPacienteIdAndBetween(Long pacienteId, Instant from, Instant to) {
        return registroRepository.findByPacienteIdAndCreatedAtBetween(pacienteId, from, to);
    }

    private void createIncidenciaAlert(Registro r) {
        // clave única para evitar duplicados
        String key = "INCIDENCIA:" + r.getPacienteId() + ":" + r.getId();

        if (alertRepository.findByDedupeKey(key).isPresent()) return;

        Alert alert = Alert.builder()
                .type(AlertType.INCIDENCIA)
                .status(AlertStatus.OPEN)
                .pacienteId(r.getPacienteId())
                .createdByUserId(r.getCreadoPorUserId())
                .message("Incidencia registrada: " + (r.getComentario() == null ? "" : r.getComentario()))
                .dedupeKey(key)
                .createdAt(Instant.now())
                .build();

        alertRepository.save(alert);
    }
}
