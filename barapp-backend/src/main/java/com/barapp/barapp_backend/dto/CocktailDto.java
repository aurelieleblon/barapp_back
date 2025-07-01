package com.barapp.barapp_backend.dto;


import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CocktailDto {

    private Long id;

    @NotBlank(message = "Le nom du cocktail est obligatoire")
    private String nom;

    @NotNull(message = "La liste des prix par taille ne peut pas être nulle")
    private List<PrixDto> prixParTaille;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<PrixDto> getPrixParTaille() {
        return prixParTaille;
    }

    public void setPrixParTaille(List<PrixDto> prixParTaille) {
        this.prixParTaille = prixParTaille;
    }
}

