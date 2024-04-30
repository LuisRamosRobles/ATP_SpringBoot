package org.example.atp_project.usuarios.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo nombre no puede estar vacío.")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacío.")
    @Column(nullable = false)
    private String apellido;

    @NotBlank(message = "El campo username no puede estar vacío.")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "El campo email no puede estar vacío.")
    @Email(regexp = ".*@.*\\..*", message = "El email introducido debe tener un formato válido.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "El campo password no puede estar vacío.")
    @Length(min = 5, message = "El campo password debe tener mínimo 5 caracteres.")
    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r-> new SimpleGrantedAuthority(r.name()))
                .collect(Collectors.toSet());
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
