package com.edu.uceva.example.facultadservice.models.services;

import com.edu.uceva.example.facultadservice.models.entities.Facultad;
import com.edu.uceva.example.facultadservice.models.repositories.IFacultadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacultadServiceImpl implements IFacultadService  {

    IFacultadRepository facultadRepository;

    public FacultadServiceImpl(IFacultadRepository facultadRepository) {
        this.facultadRepository = facultadRepository;
    }

    @Override
    @Transactional
    public Facultad save(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    @Transactional
    public void delete(Facultad facultad) {
        facultadRepository.delete(facultad);
    }

    @Override
    @Transactional(readOnly = true)
    public Facultad findById(Long id) {
        return facultadRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Facultad update(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Facultad> findAll() {
        return (List<Facultad>) facultadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Facultad> findAll(Pageable pageable) {
        return facultadRepository.findAll(pageable);
    }


}
