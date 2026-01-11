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

    private static final ZoneId ZONE = ZoneId.of("Europe/Madrid");

    private final PacienteRepository pacienteRepository;
    private final RegistroRepository registroRepository;
    private final AlertRepository alertRepository;

    /**
     * Evaluates NO_MEALS for all patients for the given date (Europe/Madrid),
     * creates OPEN alerts with dedupe, and returns how many were created.
     */
    @Override
    public int evaluateNoMealsForDate(LocalDate date) {

        Instant from = date.atStartOfDay(ZONE).toInstant();
        Instant to = date.plusDays(1).atStartOfDay(ZONE).toInstant();

        var pacientes = pacienteRepository.findAll();
        int created = 0;

        for (var p : pacientes) {

            List<Registro> regs = registroRepository
                    .findByPacienteIdAndCreatedAtBetween(p.getId(), from, to);

            boolean breakfastDone = regs.stream().anyMatch(r -> Boolean.TRUE.equals(r.getHizoDesayuno()));
            boolean lunchDone     = regs.stream().anyMatch(r -> Boolean.TRUE.equals(r.getHizoComida()));
            boolean dinnerDone    = regs.stream().anyMatch(r -> Boolean.TRUE.equals(r.getHizoCena()));

            if (!breakfastDone && !lunchDone && !dinnerDone) {
                if (createNoMealsAlert(p, date)) {
                    created++;
                }
            }
        }

        return created;
    }

    /**
     * Returns true if created, false if already existed (dedupe).
     */
    private boolean createNoMealsAlert(Paciente paciente, LocalDate date) {
        String dedupeKey = "NO_MEALS:" + paciente.getId() + ":" + date;

        if (alertRepository.findByDedupeKey(dedupeKey).isPresent()) return false;

        String patientName = paciente.getNombre() == null ? ("#" + paciente.getId()) : paciente.getNombre();

        Alert alert = Alert.builder()
                .type(AlertType.NO_MEALS)
                .status(AlertStatus.OPEN)
                .pacienteId(paciente.getId())
                .createdByUserId(null)
                .forDate(date)
                .dedupeKey(dedupeKey)
                .message("NO_MEALS: No meals recorded for patient '" + patientName + "' on " + date)
                .createdAt(Instant.now())
                .build();

        alertRepository.save(alert);
        return true;
    }
}
