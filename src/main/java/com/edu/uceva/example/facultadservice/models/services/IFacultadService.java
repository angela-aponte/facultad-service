package com.edu.uceva.example.facultadservice.models.services;

import com.edu.uceva.example.facultadservice.models.entities.Facultad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFacultadService {
    Facultad save(Facultad facultad);
    void delete(Facultad facultad);
    Facultad findById(Long id);
    Facultad update(Facultad facultad);
    List<Facultad> findAll();
    Page<Facultad> findAll(Pageable pageable);
}
