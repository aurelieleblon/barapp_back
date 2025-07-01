package com.barapp.barapp_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barapp.barapp_backend.entity.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    // Tu peux ajouter ici des méthodes personnalisées si besoin
}

