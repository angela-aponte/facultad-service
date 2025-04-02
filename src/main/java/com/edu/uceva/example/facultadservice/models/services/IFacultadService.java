package com.edu.uceva.example.facultadservice.models.services;

import com.edu.uceva.example.facultadservice.models.entities.Facultad;

import java.util.List;

public interface IFacultadService {
    Facultad save(Facultad facultad);
    void delete(Long id);
    Facultad findById(Long id);
    Facultad update(Facultad facultad);
    List<Facultad> findAll();
}
