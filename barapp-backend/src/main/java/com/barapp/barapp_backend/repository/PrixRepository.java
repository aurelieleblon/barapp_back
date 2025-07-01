package com.barapp.barapp_backend.repository;

import com.barapp.barapp_backend.entity.Prix;
import com.barapp.barapp_backend.entity.PrixId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrixRepository extends JpaRepository<Prix, PrixId> {
    // Pas besoin d’ajouter de méthode spécifique ici,
    // JpaRepository fournit déjà les CRUD de base
}

