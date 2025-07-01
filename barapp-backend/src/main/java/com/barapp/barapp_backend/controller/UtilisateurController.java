package com.barapp.barapp_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.barapp.barapp_backend.entity.Utilisateur;
import com.barapp.barapp_backend.service.UtilisateurService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> registerUtilisateur(@RequestBody Utilisateur utilisateur) {
        // Vérifier si email déjà utilisé
        Optional<Utilisateur> existingUser = utilisateurService.getUtilisateurByEmail(utilisateur.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Cet email est déjà utilisé.");
        }

        // Hacher le mot de passe
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

        // Sauvegarder l'utilisateur
        Utilisateur savedUser = utilisateurService.saveUtilisateur(utilisateur);

        return ResponseEntity.ok(savedUser);
    }

    // Nouvel endpoint pour récupérer le rôle de l'utilisateur connecté
    @GetMapping("/current-role")
    public ResponseEntity<Map<String, String>> getCurrentUserRole(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.ok(Map.of("role", "ANONYMOUS"));
        }

        String role = authentication.getAuthorities().stream()
            .findFirst()
            .map(GrantedAuthority::getAuthority)
            .orElse("ANONYMOUS");

        if (role.startsWith("ROLE_")) {
            role = role.substring(5);
        }

        return ResponseEntity.ok(Map.of("role", role));
    }
}

