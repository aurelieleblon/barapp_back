package com.barapp.barapp_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barapp.barapp_backend.entity.Prix;
import com.barapp.barapp_backend.entity.PrixId;
import com.barapp.barapp_backend.repository.PrixRepository;

@Service
public class PrixService {

    @Autowired
    private PrixRepository prixRepository;

    public List<Prix> findAll() {
        return prixRepository.findAll();
    }

    public Optional<Prix> findById(PrixId id) {
        return prixRepository.findById(id);
    }

    public Prix save(Prix prix) {
        return prixRepository.save(prix);
    }

    public void deleteById(PrixId id) {
        prixRepository.deleteById(id);
    }

     public boolean existsById(PrixId id) {
        return prixRepository.existsById(id);
    }
}

