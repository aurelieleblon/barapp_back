package com.barapp.barapp_backend.controller;

import com.barapp.barapp_backend.entity.Commande;
import com.barapp.barapp_backend.repository.CommandeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    // Récupérer toutes les commandes
    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    // Récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> commande = commandeRepository.findById(id);
        return commande.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer une nouvelle commande
    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }

    // Mettre à jour une commande existante
    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);

        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            commande.setStatutCommande(commandeDetails.getStatutCommande());
            commande.setDateCommande(commandeDetails.getDateCommande());
            commande.setUtilisateur(commandeDetails.getUtilisateur());
            Commande updatedCommande = commandeRepository.save(commande);
            return ResponseEntity.ok(updatedCommande);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        if (commandeRepository.existsById(id)) {
            commandeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

