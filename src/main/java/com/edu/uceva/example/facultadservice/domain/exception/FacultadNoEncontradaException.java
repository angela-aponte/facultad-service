package com.edu.uceva.example.facultadservice.domain.exception;

public class FacultadNoEncontradaException extends RuntimeException {
    public FacultadNoEncontradaException(Long id)
    {
        super("La facultad con ID \" + id + \" no fue encontrada.\"");
    }
}
