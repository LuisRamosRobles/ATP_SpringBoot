package org.example.atp_project.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.atp_project.usuarios.models.Roles;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {

    @NotBlank(message = "El campo nombre no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacío.")
    private String apellido;

    @NotBlank(message = "El campo username no puede estar vacío.")
    private String username;

    @NotBlank(message = "El campo email no puede estar vacío.")
    private String email;

    @NotBlank(message = "El campo password no puede estar vacío.")
    @Length(min = 5, message = "El campo password debe tener mínimo 5 caracteres.")
    private String password;

    @Builder.Default
    private Set<Roles> roles = Set.of(Roles.USER);


}
