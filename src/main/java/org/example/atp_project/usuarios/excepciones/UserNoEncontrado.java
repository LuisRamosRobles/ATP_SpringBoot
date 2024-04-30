package org.example.atp_project.usuarios.excepciones;

public class UserNoEncontrado extends UserExcepcion{
    public UserNoEncontrado(String mensaje) {
        super(mensaje);
    }

    public UserNoEncontrado(Long id) {
        super("No se ha encontrado el usuario con id: " + id);
    }
}
