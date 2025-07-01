package com.barapp.barapp_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prix")
public class Prix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // clé primaire de la table prix (ajoute si tu n'en as pas en base)

    @ManyToOne
    @JoinColumn(name = "id_cocktail")
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(name = "id_taille")
    private Taille taille;

    private Double prix;

    // Constructeurs
    public Prix() {
    }

    public Prix(Cocktail cocktail, Taille taille, Double prix) {
        this.cocktail = cocktail;
        this.taille = taille;
        this.prix = prix;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public Cocktail getCocktail() {
        return cocktail;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}

