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

import com.barapp.barapp_backend.entity.Taille;
import com.barapp.barapp_backend.repository.TailleRepository;

@RestController
@RequestMapping("/tailles")
public class TailleController {

    @Autowired
    private TailleRepository tailleRepository;

    // Récupérer toutes les tailles
    @GetMapping
    public List<Taille> getAllTailles() {
        return tailleRepository.findAll();
    }

    // Récupérer une taille par ID
    @GetMapping("/{id}")
    public ResponseEntity<Taille> getTailleById(@PathVariable Long id) {
        Optional<Taille> taille = tailleRepository.findById(id);
        if (taille.isPresent()) {
            return ResponseEntity.ok(taille.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Créer une nouvelle taille
    @PostMapping
    public Taille createTaille(@RequestBody Taille taille) {
        return tailleRepository.save(taille);
    }

    // Mettre à jour une taille existante
    @PutMapping("/{id}")
    public ResponseEntity<Taille> updateTaille(@PathVariable Long id, @RequestBody Taille tailleDetails) {
        Optional<Taille> optionalTaille = tailleRepository.findById(id);
        if (!optionalTaille.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Taille taille = optionalTaille.get();
        taille.setCode(tailleDetails.getCode());  // Met à jour le code ou autre champ que tu as
        // Ajoute d'autres setters si nécessaire

        Taille updatedTaille = tailleRepository.save(taille);
        return ResponseEntity.ok(updatedTaille);
    }

    // Supprimer une taille
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaille(@PathVariable Long id) {
        if (!tailleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tailleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
