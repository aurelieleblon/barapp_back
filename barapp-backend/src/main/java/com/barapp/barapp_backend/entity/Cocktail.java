package com.barapp.barapp_backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cocktail")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prix> prixParTaille = new ArrayList<>();

    // Constructeurs
    public Cocktail() {
    }

    public Cocktail(String nom) {
        this.nom = nom;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Prix> getPrixParTaille() {
        return prixParTaille;
    }

    public void setPrixParTaille(List<Prix> prixParTaille) {
        this.prixParTaille = prixParTaille;
    }

    // Méthode helper pour ajouter un prix
    public void addPrix(Prix prix) {
        prix.setCocktail(this);
        this.prixParTaille.add(prix);
    }
}

