package com.barapp.barapp_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.barapp.barapp_backend.entity.Ingredient;
import com.barapp.barapp_backend.repository.IngredientRepository;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // Récupérer tous les ingrédients
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // Récupérer un ingrédient par son ID
    public Optional<Ingredient> getIngredientById(Long id) {
        return ingredientRepository.findById(id);
    }

    // Créer ou mettre à jour un ingrédient
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // Supprimer un ingrédient par son ID
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}

