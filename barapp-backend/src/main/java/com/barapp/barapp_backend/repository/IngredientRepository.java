package com.barapp.barapp_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barapp.barapp_backend.entity.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}

