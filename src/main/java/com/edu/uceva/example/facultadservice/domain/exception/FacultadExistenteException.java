package com.edu.uceva.example.facultadservice.domain.exception;

public class FacultadExistenteException extends RuntimeException {
    public FacultadExistenteException(String nombre)
    {
        super("El faccultad con nombre '" + nombre + "' ya existe." );
    }
}
