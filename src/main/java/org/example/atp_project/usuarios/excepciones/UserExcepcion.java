package org.example.atp_project.usuarios.excepciones;

public abstract class UserExcepcion extends RuntimeException{

    public UserExcepcion(String mensaje) {
        super(mensaje);
    }

}
