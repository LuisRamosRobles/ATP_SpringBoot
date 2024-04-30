package org.example.atp_project.auth.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthSingInInvalid extends AuthExcepcion{
    public AuthSingInInvalid(String mensaje) {
        super(mensaje);
    }
}
