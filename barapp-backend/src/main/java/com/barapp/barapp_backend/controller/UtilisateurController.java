package com.barapp.barapp_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barapp.barapp_backend.entity.Utilisateur;
import com.barapp.barapp_backend.repository.UtilisateurRepository;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Lister tous les utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouvel utilisateur
    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);

        if (!utilisateurOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Utilisateur utilisateur = utilisateurOptional.get();
        utilisateur.setNom(utilisateurDetails.getNom());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
        utilisateur.setRole(utilisateurDetails.getRole());

        Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        if (!utilisateurRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        utilisateurRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

