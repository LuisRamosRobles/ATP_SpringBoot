package org.example.atp_project.excepciones.torneo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ListaLlenaException extends TorneoExcepcion {
    public ListaLlenaException(String mensaje) {
        super(mensaje);
    }
}
