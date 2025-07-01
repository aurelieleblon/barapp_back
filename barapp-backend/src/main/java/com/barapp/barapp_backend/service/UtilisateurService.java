package com.barapp.barapp_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.barapp.barapp_backend.entity.Utilisateur;
import com.barapp.barapp_backend.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        // Hacher le mot de passe avant d'enregistrer
        String motDePasseClair = utilisateur.getMotDePasse();
        if (motDePasseClair != null) {
            String motDePasseEncode = passwordEncoder.encode(motDePasseClair);
            utilisateur.setMotDePasse(motDePasseEncode);
        }
        return utilisateurRepository.save(utilisateur);
    }

    public boolean deleteUtilisateur(Long id) {
        if(utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}


