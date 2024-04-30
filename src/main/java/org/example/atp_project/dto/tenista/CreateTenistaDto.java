package org.example.atp_project.dto.tenista;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.example.atp_project.models.Tenista;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Builder
public class CreateTenistaDto {

    private final Integer ranking;

    @NotBlank(message = "El campo nombre completo no puede estar vacío.")
    @Length(min = 5, message = "El nombre completo no puede tener menos de 5 caracteres.")
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "El campo nombre completo no puede contener números.")
    private final String nombreCompleto;

    @NotBlank(message = "El campo país no puede estar vacío.")
    @Length(min = 4, message = "El país no puede tener menos de 4 caracteres.")
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "El campo pais no puede contener números.")
    private final String pais;

    @NotNull(message = "El campo fecha de nacimiento no puede estar vacío.")
    private final LocalDate fechaNac;

    private final Integer edad;

    @NotNull(message = "El campo altura no puede estar vacío.")
    @DecimalMin(value = "1.00", message="El tenista no puede medir menos de 1 metro.")
    private final Double altura;

    @NotNull(message = "El campo peso no puede estar vacío.")
    @DecimalMin(value = "30.00", message="El tenista no puede pesar menos de 30 kilos.")
    private final Double peso;

    @NotNull(message = "El campo profesional desde no puede estar vacío.")
    private final LocalDate profesionalDesde;

    @NotNull(message = "El campo mano preferida no puede estar vacío.")
    private final Tenista.Mano manoPref;

    @NotNull(message = "El campo reves no puede estar vacío.")
    private final Tenista.Reves reves;

    @NotBlank(message = "El campo entrenador no puede estar vacío.")
    @Length(min = 5, message = "El entrenador no puede tener menos de 5 caracteres.")
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "El campo entrenador no puede contener números.")
    private final String entrenador;

    @NotNull(message = "El campo precio no puede estar vacío.")
    @DecimalMin(value = "0.0", message="El campo priceMoney no puede ser negativo.")
    private final Double priceMoney;

    @NotNull(message = "El campo win no puede estar vacío.")
    @Min(value = 0, message="El campo win no puede ser negativo.")
    private final Integer win;

    private final String win_rate;

    @NotNull(message = "El campo lost no puede estar vacío.")
    @Min(value = 0, message="El campo lost no puede ser negativo.")
    private final Integer lost;

    //@NotBlank(message = "El campo imagen no puede estar vacío.")
    private final String imagen;

    @NotNull(message = "El campo puntos no puede estar vacío.")
    @Min(value = 0, message="El campo puntos no puede ser negativo.")
    private final Integer puntos;

}
