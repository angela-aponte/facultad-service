package com.edu.uceva.example.facultadservice.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {
    private Long id;
    private String nombreCompleto;
    private String correo;
    private String contrasena;
    private Long cedula;
    private Long telefono;
    private String rol;
}
