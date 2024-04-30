package org.example.atp_project.auth.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAuthNameOrEmailExisten extends AuthExcepcion{
    public UserAuthNameOrEmailExisten(String mensaje) {
        super(mensaje);
    }
}
