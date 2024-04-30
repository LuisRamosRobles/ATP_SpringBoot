package org.example.atp_project.auth.services.users;

import org.example.atp_project.auth.repo.AuthUsersRepository;
import org.example.atp_project.usuarios.excepciones.UserNoEncontrado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthUsersServiceImpl implements AuthUsersService{

    private Logger log = LoggerFactory.getLogger(AuthUsersServiceImpl.class);

    private final AuthUsersRepository authUsersRepository;

    @Autowired
    public AuthUsersServiceImpl(AuthUsersRepository authUsersRepository) {
        this.authUsersRepository = authUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNoEncontrado {
        log.info("Llegue a loadUserByUsername");

        var auth = authUsersRepository.findByUsername(username)
                .orElseThrow(() -> new UserNoEncontrado("Usuario con username " + username + " no encontrado"));

        log.info("Devolviendo loadUserByUsername" + auth.toString());

        return auth;
    }
}
