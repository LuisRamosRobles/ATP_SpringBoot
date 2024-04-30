package org.example.atp_project.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.atp_project.usuarios.models.Roles;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUser {

    private Long id;

    private String nombre;

    private String apellido;

    private String username;

    private String email;

    @Builder.Default
    private Set<Roles> roles = Set.of(Roles.USER);


}
