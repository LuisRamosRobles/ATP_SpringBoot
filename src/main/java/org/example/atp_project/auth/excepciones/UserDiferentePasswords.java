package org.example.atp_project.auth.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDiferentePasswords extends AuthExcepcion{
    public UserDiferentePasswords(String mensaje) {
        super(mensaje);
    }
}
