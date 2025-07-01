package com.barapp.barapp_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barapp.barapp_backend.entity.Utilisateur;
import com.barapp.barapp_backend.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    // Créer ou mettre à jour un utilisateur
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // Supprimer un utilisateur par ID
    public boolean deleteUtilisateur(Long id) {
        if(utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Recherche par email (optionnel)
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}

