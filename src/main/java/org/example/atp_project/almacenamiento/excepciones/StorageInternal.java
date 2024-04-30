package org.example.atp_project.almacenamiento.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class StorageInternal extends StorageException{
    public StorageInternal(String mensaje) {
        super(mensaje);
    }
}
