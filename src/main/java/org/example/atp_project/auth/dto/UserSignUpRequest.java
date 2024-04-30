package org.example.atp_project.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpRequest {
    @NotBlank(message = "El campo nombre no puede estar vacío.")
    private String nombre;
    @NotBlank(message = "El campo apellidos no puede estar vacío.")
    private String apellidos;
    @NotBlank(message = "El campo username no puede estar vacío.")
    private String username;
    @Email(regexp = ".*@.*\\..*", message = "El campo email debe ser válido.")
    @NotBlank(message = "El campo email no puede estar vacío.")
    private String email;
    @NotBlank(message = "El campo password no puede estar vacío.")
    @Length(min = 5, message = "El campo password debe tener al menos 5 caracteres.")
    private String password;
    @NotBlank(message = "El campo password comprobación no puede estar vacío.")
    @Length(min = 5, message = "El campo password comprobación debe tener al menos 5 caracteres.")
    private String passwordComprobacion;
}
