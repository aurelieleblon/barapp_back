package com.barapp.barapp_backend.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.barapp.barapp_backend.dto.CocktailDto;
import com.barapp.barapp_backend.dto.PrixDto;
import com.barapp.barapp_backend.entity.Cocktail;
import com.barapp.barapp_backend.entity.Prix;

public class CocktailControllerTest {

    @Test
    public void testConvertToEntity() {
        CocktailController controller = new CocktailController(null); // service non nécessaire ici

        // Prépare un CocktailDto avec un prix
        CocktailDto dto = new CocktailDto();
        dto.setNom("Test Cocktail");

        PrixDto prixDto = new PrixDto();
        prixDto.setPrix(5.0);
        dto.setPrixParTaille(List.of(prixDto));

        // Conversion
        Cocktail cocktail = controller.convertToEntity(dto);

        assertEquals("Test Cocktail", cocktail.getNom());
        assertNotNull(cocktail.getPrixParTaille());
        assertEquals(1, cocktail.getPrixParTaille().size());

        Prix prix = cocktail.getPrixParTaille().get(0);
        assertEquals(5.0, prix.getPrix());
    }
}


