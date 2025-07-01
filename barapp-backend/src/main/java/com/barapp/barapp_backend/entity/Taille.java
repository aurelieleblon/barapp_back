package com.barapp.barapp_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "taille")
public class Taille {

    @Id
    private Long id;  // Correspond à id_taille en base

    private String code; // Exemple : "S", "M", "L"

    // Constructeurs
    public Taille() {
    }

    public Taille(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

