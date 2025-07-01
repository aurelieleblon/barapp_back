package com.barapp.barapp_backend.controller;

import com.barapp.barapp_backend.entity.Prix;
import com.barapp.barapp_backend.entity.PrixId;
import com.barapp.barapp_backend.service.PrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prix")
public class PrixController {

    @Autowired
    private PrixService prixService;

    // Récupérer tous les prix
    @GetMapping
    public List<Prix> getAllPrix() {
        return prixService.findAll();
    }

    // Récupérer un prix par clé composite (cocktail + taille)
    @GetMapping("/cocktail/{cocktailId}/taille/{tailleId}")
    public ResponseEntity<Prix> getPrix(
            @PathVariable Long cocktailId,
            @PathVariable Long tailleId) {

        PrixId id = new PrixId(cocktailId, tailleId);
        return prixService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer un nouveau prix
    @PostMapping
    public Prix createPrix(@RequestBody Prix prix) {
        return prixService.save(prix);
    }

    // Mettre à jour un prix existant
    @PutMapping("/cocktail/{cocktailId}/taille/{tailleId}")
    public ResponseEntity<Prix> updatePrix(
            @PathVariable Long cocktailId,
            @PathVariable Long tailleId,
            @RequestBody Prix prixDetails) {

        PrixId id = new PrixId(cocktailId, tailleId);
        return prixService.findById(id)
                .map(prix -> {
                    prix.setPrix(prixDetails.getPrix());
                    return ResponseEntity.ok(prixService.save(prix));
                }).orElse(ResponseEntity.notFound().build());
    }

    // Supprimer un prix
    @DeleteMapping("/cocktail/{cocktailId}/taille/{tailleId}")
    public ResponseEntity<Void> deletePrix(
            @PathVariable Long cocktailId,
            @PathVariable Long tailleId) {

        PrixId id = new PrixId(cocktailId, tailleId);
        if (prixService.existsById(id)) {
            prixService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
