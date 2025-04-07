package com.edu.uceva.example.facultadservice.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message ="No puede estar vacio")
    @Size(min=2, max=20, message="El tamaño tiene que estar entre 2 y 20")
    @Column(nullable=false)

    private String nombre;
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")

    @NotEmpty(message = "Debe ingresar el nombre de la facultad")
    @Column(nullable = false)
    private Long id_decano;

}
