package patientcaretrackbackend.patients.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import patientcaretrackbackend.patients.application.dto.PacienteDetailDto;
import patientcaretrackbackend.patients.application.dto.PacienteSummaryDto;
import patientcaretrackbackend.patients.domain.model.Paciente;
import patientcaretrackbackend.patients.infrastructure.web.dto.PacienteCreateRequest;
import patientcaretrackbackend.patients.infrastructure.web.dto.PacienteUpdateRequest;

@Component
public class PacienteDtoMapper {

    public PacienteSummaryDto toSummaryDto(Paciente p) {
        return new PacienteSummaryDto(
                p.getId(),
                p.getNombre(),
                p.getEdad(),
                p.getImageUrl(),
                p.getAssignedUserId()
        );
    }

    public PacienteDetailDto toDetailDto(Paciente p) {
        return new PacienteDetailDto(
                p.getId(),
                p.getNombre(),
                p.getEdad(),
                p.getHistorialMedico(),
                p.getObservacionesGenerales(),
                p.getImageUrl(),
                p.getAssignedUserId()
        );
    }

    public Paciente fromCreateRequest(PacienteCreateRequest req) {
        return Paciente.builder()
                .nombre(req.nombre())
                .edad(req.edad())
                .historialMedico(req.historialMedico())
                .observacionesGenerales(req.observacionesGenerales())
                .imageUrl(req.imageUrl())
                .assignedUserId(req.assignedUserId())
                .build();
    }

    public Paciente fromUpdateRequest(Long id, PacienteUpdateRequest req) {
        return Paciente.builder()
                .id(id)
                .nombre(req.nombre())
                .edad(req.edad())
                .historialMedico(req.historialMedico())
                .observacionesGenerales(req.observacionesGenerales())
                .imageUrl(req.imageUrl())
                .assignedUserId(req.assignedUserId())
                .build();
    }
}
