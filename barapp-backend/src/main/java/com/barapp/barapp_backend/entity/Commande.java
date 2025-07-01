package com.barapp.barapp_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "statut_commande", nullable = false)
    private String statutCommande;  // "Commandée", "En cours", "Terminée"

    @Column(name = "date_commande", nullable = false)
    private LocalDateTime dateCommande;

    // @OneToMany(mappedBy = "commande")
    // private List<CommandeCocktail> cocktails;

    // Constructeurs
    public Commande() {}

    // Getters et setters
    public Long getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    // public List<CommandeCocktail> getCocktails() {
    //     return cocktails;
    // }

    // public void setCocktails(List<CommandeCocktail> cocktails) {
    //     this.cocktails = cocktails;
    // }
}

