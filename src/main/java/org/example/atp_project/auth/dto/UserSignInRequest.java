package org.example.atp_project.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInRequest {
    @NotBlank(message = "El campo username no puede estar vacío.")
    private String username;
    @NotBlank(message = "El campo password no puede estar vacío.")
    @Length(min = 5, message = "El campo password debe tener al menos 5 caracteres")
    private String password;
}
