package com.barapp.barapp_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barapp.barapp_backend.entity.Cocktail;
import com.barapp.barapp_backend.service.CocktailService;

@RestController
@RequestMapping("/api/cocktails")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public List<Cocktail> getAllCocktails() {
        return cocktailService.getAllCocktails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cocktail> getCocktailById(@PathVariable Long id) {
        return cocktailService.getCocktailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cocktail createCocktail(@RequestBody Cocktail cocktail) {
        return cocktailService.saveCocktail(cocktail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable Long id) {
        cocktailService.deleteCocktail(id);
        return ResponseEntity.noContent().build();
    }
}

