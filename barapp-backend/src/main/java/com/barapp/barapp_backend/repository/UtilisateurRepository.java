package com.barapp.barapp_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barapp.barapp_backend.entity.Utilisateur;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Recherche par email (utile pour l’authentification)
    Optional<Utilisateur> findByEmail(String email);

    // Recherche par rôle (client ou barmaker)
    List<Utilisateur> findByRole(String role);
}

