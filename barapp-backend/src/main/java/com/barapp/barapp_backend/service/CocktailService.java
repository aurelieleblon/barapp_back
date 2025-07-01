package com.barapp.barapp_backend.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.barapp.barapp_backend.entity.Cocktail;
import com.barapp.barapp_backend.repository.CocktailRepository;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List<Cocktail> getAllCocktails() {
        Set<Cocktail> cocktailsSet = cocktailRepository.findAllWithPrix();
        // Conversion en liste pour le controller
        return cocktailsSet.stream().collect(Collectors.toList());
    }

    public java.util.Optional<Cocktail> getCocktailById(Long id) {
        return cocktailRepository.findById(id);
    }

    public Cocktail saveCocktail(Cocktail cocktail) {
        return cocktailRepository.save(cocktail);
    }

    public void deleteCocktail(Long id) {
        cocktailRepository.deleteById(id);
    }
}

