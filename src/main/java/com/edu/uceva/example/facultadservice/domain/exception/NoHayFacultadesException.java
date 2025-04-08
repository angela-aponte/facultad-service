package com.edu.uceva.example.facultadservice.domain.exception;

public class NoHayFacultadesException extends RuntimeException {
    public NoHayFacultadesException()
    {
        super("No hay facultades en la base de datos");
    }
}
