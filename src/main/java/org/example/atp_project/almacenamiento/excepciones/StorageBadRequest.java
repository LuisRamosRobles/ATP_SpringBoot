package org.example.atp_project.almacenamiento.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StorageBadRequest extends StorageException {
    public StorageBadRequest(String mensaje) {
        super(mensaje);
    }
}
