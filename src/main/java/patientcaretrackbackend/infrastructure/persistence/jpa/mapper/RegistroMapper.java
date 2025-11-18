package patientcaretrackbackend.infrastructure.persistence.jpa.mapper;

import patientcaretrackbackend.domain.model.Registro;
import patientcaretrackbackend.infrastructure.persistence.jpa.entity.RegistroEntity;

public class RegistroMapper implements Mapper<Registro, RegistroEntity> {

    @Override
    public Registro toDomain(RegistroEntity e) {
        if (e == null) return null;
        return Registro.builder()
                .id(e.getId())
                .pacienteId(e.getPacienteId())
                .creadoPorUserId(e.getCreadoPorUserId())
                .tipo(e.getTipo())
                .comentario(e.getComentario())
                .createdAt(e.getCreatedAt())
                .hizoDeposicion(e.getHizoDeposicion())
                .deposicionCalidad(e.getDeposicionCalidad())
                .hizoDesayuno(e.getHizoDesayuno())
                .hizoComida(e.getHizoComida())
                .hizoCena(e.getHizoCena())
                .liquidosMl(e.getLiquidosMl())
                .aseoTipo(e.getAseoTipo())
                .notificarIncidencia(e.getNotificarIncidencia())
                .build();
    }

    @Override
    public RegistroEntity toEntity(Registro d) {
        if (d == null) return null;
        return RegistroEntity.builder()
                .id(d.getId())
                .pacienteId(d.getPacienteId())
                .creadoPorUserId(d.getCreadoPorUserId())
                .tipo(d.getTipo())
                .comentario(d.getComentario())
                .createdAt(d.getCreatedAt())
                .hizoDeposicion(d.getHizoDeposicion())
                .deposicionCalidad(d.getDeposicionCalidad())
                .hizoDesayuno(d.getHizoDesayuno())
                .hizoComida(d.getHizoComida())
                .hizoCena(d.getHizoCena())
                .liquidosMl(d.getLiquidosMl())
                .aseoTipo(d.getAseoTipo())
                .notificarIncidencia(d.getNotificarIncidencia())
                .build();
    }
}
