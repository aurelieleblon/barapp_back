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

import com.barapp.barapp_backend.dto.CocktailDto;
import com.barapp.barapp_backend.dto.PrixDto;
import com.barapp.barapp_backend.entity.Cocktail;
import com.barapp.barapp_backend.entity.Prix;
import com.barapp.barapp_backend.service.CocktailService;

import jakarta.validation.Valid;


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
public ResponseEntity<CocktailDto> createCocktail(@Valid @RequestBody CocktailDto cocktailDto) {
    Cocktail cocktail = convertToEntity(cocktailDto);
    Cocktail savedCocktail = cocktailService.saveCocktail(cocktail);
    CocktailDto savedDto = convertToDto(savedCocktail);
    return ResponseEntity.ok(savedDto);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable Long id) {
        cocktailService.deleteCocktail(id);
        return ResponseEntity.noContent().build();
    }

    public Cocktail convertToEntity(CocktailDto dto) {
    Cocktail cocktail = new Cocktail();
    cocktail.setNom(dto.getNom());
    if (dto.getPrixParTaille() != null) {
        dto.getPrixParTaille().forEach(prixDto -> {
            Prix prix = new Prix();
            prix.setPrix(prixDto.getPrix());
            cocktail.addPrix(prix);
        });
    }
    return cocktail;
}

CocktailDto convertToDto(Cocktail cocktail) {
    CocktailDto dto = new CocktailDto();
    dto.setId(cocktail.getId());
    dto.setNom(cocktail.getNom());

    List<PrixDto> prixDtos = cocktail.getPrixParTaille().stream().map(prix -> {
        PrixDto prixDto = new PrixDto();
        prixDto.setId(prix.getId());
        prixDto.setPrix(prix.getPrix());
        return prixDto;
    }).toList();

    dto.setPrixParTaille(prixDtos);
    return dto;
}

}

