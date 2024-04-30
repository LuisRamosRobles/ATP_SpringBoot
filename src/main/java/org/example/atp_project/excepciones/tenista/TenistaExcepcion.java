package org.example.atp_project.excepciones.tenista;

public abstract class TenistaExcepcion extends RuntimeException {

    public TenistaExcepcion(String mensaje) {
        super(mensaje);
    }

}
