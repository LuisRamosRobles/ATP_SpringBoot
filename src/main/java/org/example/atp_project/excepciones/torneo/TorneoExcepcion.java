package org.example.atp_project.excepciones.torneo;

public abstract class TorneoExcepcion extends RuntimeException {

    public TorneoExcepcion(String mensaje) {
        super(mensaje);
    }
}
