package org.example.atp_project.dto.tenista;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.atp_project.models.Tenista;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class UpdateTenistaDto {

    private final Integer ranking;

    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "El campo nombre completo no puede contener números.")
    @Length(min = 5, message = "El nombre completo no puede tener menos de 5 caracteres.")
    private final String nombreCompleto;

    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "El campo pais no puede contener números.")
    @Length(min = 4, message = "El pais no puede tener menos de 4 caracteres.")
    private final String pais;

    private final LocalDate fechaNac;

    private final Integer edad;

    @DecimalMin(value = "0", message="El campo altura no puede ser negativo.")
    private final Double altura;

    @DecimalMin(value = "0", message="El campo peso no puede ser negativo.")
    private final Double peso;

    private final LocalDate profesionalDesde;

    private final Tenista.Mano manoPref;

    private final Tenista.Reves reves;

    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "El campo entrenador no puede contener números.")
    @Length(min = 5, message = "El entrenador no puede tener menos de 5 caracteres.")
    private final String entrenador;

    @DecimalMin(value = "0", message="El campo priceMoney no puede ser negativo.")
    private final Double priceMoney;

    @Min(value = 0, message="El campo win no puede ser negativo.")
    private final Integer win;

    @Min(value = 0, message="El campo lost no puede ser negativo.")
    private final Integer lost;

    private final String win_rate;

    private final String imagen;

    @Min(value = 0, message="El campo puntos no puede ser negativo.")
    private final Integer puntos;


}
