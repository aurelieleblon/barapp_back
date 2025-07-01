package com.barapp.barapp_backend.controller;

import com.barapp.barapp_backend.entity.Ingredient;
import com.barapp.barapp_backend.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    // Récupérer tous les ingrédients
    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // Récupérer un ingrédient par ID
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return ingredient.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouvel ingrédient
    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // Mettre à jour un ingrédient existant
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredientDetails) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (!optionalIngredient.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Ingredient ingredient = optionalIngredient.get();
        ingredient.setNom(ingredientDetails.getNom());
        Ingredient updatedIngredient = ingredientRepository.save(ingredient);
        return ResponseEntity.ok(updatedIngredient);
    }

    // Supprimer un ingrédient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        if (!ingredientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ingredientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

