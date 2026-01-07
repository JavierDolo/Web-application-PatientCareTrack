package patientcaretrackbackend.patients.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.patients.application.usecase.AlertEvaluationUseCase;
import patientcaretrackbackend.patients.domain.model.*;
import patientcaretrackbackend.patients.domain.port.AlertRepository;
import patientcaretrackbackend.patients.domain.port.PacienteRepository;
import patientcaretrackbackend.patients.domain.port.RegistroRepository;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoMealsAlertEvaluator implements AlertEvaluationUseCase {

    private final PacienteRepository pacienteRepository;
    private final RegistroRepository registroRepository;
    private final AlertRepository alertRepository;

    @Override
    public void evaluateNoMealsForDate(LocalDate date) {

        Instant from = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant to = date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        var pacientes = pacienteRepository.findAll();

        for (var p : pacientes) {
            List<Registro> regs = registroRepository.findByPacienteIdAndCreatedAtBetween(p.getId(), from, to);

            boolean anyMealDone = regs.stream().anyMatch(this::isMealDone);

            if (!anyMealDone) {
                createNoMealsAlert(p.getId(), date);
            }
        }
    }

    private boolean isMealDone(Registro r) {
        if (r.getTipo() == null) return false;

        return switch (r.getTipo()) {
            case DESAYUNO -> Boolean.TRUE.equals(r.getHizoDesayuno());
            case COMIDA -> Boolean.TRUE.equals(r.getHizoComida());
            case CENA -> Boolean.TRUE.equals(r.getHizoCena());
            default -> false;
        };
    }

    private void createNoMealsAlert(Long pacienteId, LocalDate date) {
        String dedupeKey = "NO_MEALS:" + pacienteId + ":" + date;

        // Evitar duplicados
        if (alertRepository.findByDedupeKey(dedupeKey).isPresent()) return;

        Alert alert = Alert.builder()
                .type(AlertType.NO_MEALS)
                .status(AlertStatus.OPEN)
                .pacienteId(pacienteId)
                .createdByUserId(null)
                .message("No hay comidas registradas (DESAYUNO/COMIDA/CENA) para el día " + date)
                .forDate(date)
                .dedupeKey(dedupeKey)
                .createdAt(Instant.now())
                .build();

        alertRepository.save(alert);
    }
}
