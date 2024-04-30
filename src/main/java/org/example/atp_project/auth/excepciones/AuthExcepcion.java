package org.example.atp_project.auth.excepciones;

public abstract class AuthExcepcion extends RuntimeException{

    public AuthExcepcion(String mensaje) {
        super(mensaje);
    }
}
