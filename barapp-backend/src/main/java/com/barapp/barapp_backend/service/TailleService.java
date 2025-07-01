package com.barapp.barapp_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barapp.barapp_backend.entity.Taille;
import com.barapp.barapp_backend.repository.TailleRepository;

@Service
public class TailleService {

    @Autowired
    private TailleRepository tailleRepository;

    public List<Taille> getAllTailles() {
        return tailleRepository.findAll();
    }

    public Optional<Taille> getTailleById(Long id) {
        return tailleRepository.findById(id);
    }

    public Taille saveTaille(Taille taille) {
        return tailleRepository.save(taille);
    }

    public void deleteTaille(Long id) {
        tailleRepository.deleteById(id);
    }
}
