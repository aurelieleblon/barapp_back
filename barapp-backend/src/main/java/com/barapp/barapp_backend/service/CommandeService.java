package com.barapp.barapp_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barapp.barapp_backend.entity.Commande;
import com.barapp.barapp_backend.repository.CommandeRepository;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Long id, Commande commandeDetails) {
        return commandeRepository.findById(id).map(commande -> {
            commande.setStatutCommande(commandeDetails.getStatutCommande());
            commande.setDateCommande(commandeDetails.getDateCommande());
            commande.setUtilisateur(commandeDetails.getUtilisateur());
            // commande.setCocktails(commandeDetails.getCocktails());
            return commandeRepository.save(commande);
        }).orElseThrow(() -> new RuntimeException("Commande not found with id " + id));
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}

