package org.example.atp_project.almacenamiento.excepciones;

public abstract class StorageException extends RuntimeException {

    public StorageException(String mensaje) {
        super(mensaje);
    }

}
