package org.example.atp_project.usuarios.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameEmailExiste extends UserExcepcion {
    public UsernameEmailExiste(String mensaje) {
        super(mensaje);
    }
}
