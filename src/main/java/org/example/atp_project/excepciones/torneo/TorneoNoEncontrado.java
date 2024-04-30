package org.example.atp_project.excepciones.torneo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class TorneoNoEncontrado extends TorneoExcepcion{
    public TorneoNoEncontrado(Long id) {
        super("El id ( " + id + " ) no corresponde a ningún torneo.");
    }
}
