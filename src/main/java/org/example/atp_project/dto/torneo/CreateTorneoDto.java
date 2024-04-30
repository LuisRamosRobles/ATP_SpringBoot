package org.example.atp_project.dto.torneo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.atp_project.models.Torneo;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateTorneoDto {

    @NotBlank(message = "El campo nombre no puede estar vacío.")
    @Length(min = 5, message = "El nombre no puede tener menos de 5 caracteres.")
    private final String nombre;

    @NotBlank(message = "El campo ubicación no puede estar vacío.")
    @Length(min = 5, message = "La ubicación no puede tener menos de 5 caracteres.")
    private final String ubicacion;

    @NotNull(message = "El campo tipo torneo no puede estar vacío.")
    private final Torneo.TipoTorneo tipoTorneo;

    @NotNull(message = "El campo categoria no puede estar vacío.")
    private final Torneo.Categoria categoria;

    @NotNull(message = "El campo superficie no puede estar vacío.")
    private final Torneo.Superficie superficie;

    @NotNull(message = "El campo entradas no puede estar estar vacío.")
    @Min(value = 0, message="El campo entradas no puede ser negativo.")
    private final Integer entradas;

    @NotNull(message = "El campo fecha_ini no puede estar vacío.")
    private final LocalDate fecha_ini;

    @NotNull(message = "El campo fecha_fin no puede estar vacío.")
    private final LocalDate fecha_fin;

    @NotNull(message = "El campo premio no puede estar vacío.")
    @DecimalMin(value = "0.0", message = "El premio no puede ser negativo.")
    private final Double premio;

    @NotBlank(message = "El campo imagen no puede estar vacío.")
    private final String imagen;

    @NotNull(message = "El campo tenistas_total no puede estar vacío.")
    @Min(value = 0, message="El campo tenistas_total no puede ser negativo.")
    private final Integer tenistas_total;
}
