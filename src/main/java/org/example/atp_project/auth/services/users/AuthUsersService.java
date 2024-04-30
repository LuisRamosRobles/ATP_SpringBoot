package org.example.atp_project.auth.services.users;

import org.example.atp_project.usuarios.excepciones.UserNoEncontrado;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUsersService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UserNoEncontrado;
}
