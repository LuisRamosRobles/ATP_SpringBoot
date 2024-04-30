package org.example.atp_project.dto.torneo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.example.atp_project.models.Torneo;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UpdateTorneoDto {

    @Length(min = 5, message = "El nombre no puede tener menos de 5 caracteres.")
    private final String nombre;

    @Length(min = 5, message = "La ubicación no puede tener menos de 5 caracteres.")
    private final String ubicacion;

    private final Torneo.TipoTorneo tipoTorneo;

    private final Torneo.Categoria categoria;

    private final Torneo.Superficie superficie;

    @Min(value = 0, message = "El campo entradas no puede ser negativo.")
    private final Integer entradas;

    private final LocalDate fecha_ini;

    private final LocalDate fecha_fin;

    @DecimalMin(value = "0.0", message = "El premio no puede ser negativo.")
    private final Double premio;

    private final String imagen;

    private final Integer tenistas_total;


}
