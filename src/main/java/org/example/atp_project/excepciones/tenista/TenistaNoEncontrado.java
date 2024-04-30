package org.example.atp_project.excepciones.tenista;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistaNoEncontrado extends TenistaExcepcion {


    public TenistaNoEncontrado(Long id) {
        super("El id ( " + id + " ) no corresponde a ningún tenista.");
    }
}
