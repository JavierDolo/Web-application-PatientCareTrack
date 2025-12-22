package patientcaretrackbackend.patients.infrastructure.web.dto.records;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import patientcaretrackbackend.patients.domain.model.TipoRegistro;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "tipo",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegistroComidaRequest.class, name = "DESAYUNO"),
        @JsonSubTypes.Type(value = RegistroComidaRequest.class, name = "COMIDA"),
        @JsonSubTypes.Type(value = RegistroComidaRequest.class, name = "CENA"),
        @JsonSubTypes.Type(value = RegistroDeposicionRequest.class, name = "DEPOSICION"),
        @JsonSubTypes.Type(value = RegistroLiquidosRequest.class, name = "LIQUIDOS"),
        @JsonSubTypes.Type(value = RegistroAseoRequest.class, name = "ASEO")
})
public interface RegistroBaseRequest {
    TipoRegistro tipo();
    String comentario();
    Boolean notificarIncidencia();
}
