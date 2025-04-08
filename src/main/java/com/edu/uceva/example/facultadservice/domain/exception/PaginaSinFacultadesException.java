package com.edu.uceva.example.facultadservice.domain.exception;

public class PaginaSinFacultadesException extends RuntimeException {
    public PaginaSinFacultadesException(int page)
    {
        super("No hay facultades en la página solicitada: " + page);
    }
}
