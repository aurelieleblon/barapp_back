package com.barapp.barapp_backend.entity;

import java.io.Serializable;
import java.util.Objects;

public class PrixId implements Serializable {
    private Long cocktail;
    private Long taille;

    public PrixId() {}

    public PrixId(Long cocktail, Long taille) {
        this.cocktail = cocktail;
        this.taille = taille;
    }

    // equals() et hashCode() obligatoires

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrixId)) return false;
        PrixId that = (PrixId) o;
        return Objects.equals(cocktail, that.cocktail) &&
               Objects.equals(taille, that.taille);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktail, taille);
    }

    // getters et setters
    public Long getCocktail() { return cocktail; }
    public void setCocktail(Long cocktail) { this.cocktail = cocktail; }
    public Long getTaille() { return taille; }
    public void setTaille(Long taille) { this.taille = taille; }
}

