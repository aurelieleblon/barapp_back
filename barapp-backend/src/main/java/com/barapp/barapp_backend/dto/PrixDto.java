package com.barapp.barapp_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PrixDto {

    private Long id;

    @NotNull(message = "Le prix ne doit pas être nul")
    @Min(value = 0, message = "Le prix doit être positif ou nul")
    private Double prix;

    public PrixDto(Double prix) {
    this.prix = prix;
}

public PrixDto() {
    // constructeur vide obligatoire si tu as un autre constructeur
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}


