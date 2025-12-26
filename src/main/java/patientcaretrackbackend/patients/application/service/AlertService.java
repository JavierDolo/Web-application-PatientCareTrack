package patientcaretrackbackend.patients.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.patients.application.dto.AlertDto;
import patientcaretrackbackend.patients.application.usecase.AlertUseCase;
import patientcaretrackbackend.patients.application.usecase.PacienteUseCase;
import patientcaretrackbackend.patients.application.usecase.RegistroUseCase;
import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.domain.model.Registro;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService implements AlertUseCase {

    private final PacienteUseCase pacienteUseCase;     // para obtener pacientes (admin)
    private final RegistroUseCase registroUseCase;     // para leer registros por día

    @Override
    public List<AlertDto> alertsForDate(LocalDate date) {

        ZoneId zone = ZoneId.systemDefault();
        Instant from = date.atStartOfDay(zone).toInstant();
        Instant to = date.plusDays(1).atStartOfDay(zone).toInstant();

        List<Paciente> pacientes = pacienteUseCase.all(); // admin ve todos
        List<AlertDto> alerts = new ArrayList<>();

        for (Paciente p : pacientes) {
            List<Registro> regs = registroUseCase.findByPacienteIdAndBetween(p.getId(), from, to);

            // 1) Incidencias manuales
            boolean hasIncident = regs.stream().anyMatch(r -> Boolean.TRUE.equals(r.getNotificarIncidencia()));
            if (hasIncident) {
                alerts.add(new AlertDto(
                        "INCIDENT",
                        p.getId(),
                        p.getNombre(),
                        date,
                        "An issue has been flagged in today’s records."
                ));
            }

            // 2) Día sin comidas (no hay DESAYUNO/COMIDA/CENA con true)
            boolean ateBreakfast = regs.stream().anyMatch(r -> r.getTipo() == TipoRegistro.DESAYUNO && Boolean.TRUE.equals(r.getHizoDesayuno()));
            boolean ateLunch     = regs.stream().anyMatch(r -> r.getTipo() == TipoRegistro.COMIDA   && Boolean.TRUE.equals(r.getHizoComida()));
            boolean ateDinner    = regs.stream().anyMatch(r -> r.getTipo() == TipoRegistro.CENA     && Boolean.TRUE.equals(r.getHizoCena()));

            if (!ateBreakfast && !ateLunch && !ateDinner) {
                alerts.add(new AlertDto(
                        "NO_MEALS",
                        p.getId(),
                        p.getNombre(),
                        date,
                        "No meals (breakfast, lunch, or dinner) have been logged for today."
                ));
            }
        }

        return alerts;
    }
}
