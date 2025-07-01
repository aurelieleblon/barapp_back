package com.barapp.barapp_backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.barapp.barapp_backend.entity.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

   @Query("SELECT DISTINCT c FROM Cocktail c LEFT JOIN FETCH c.prixParTaille")
Set<Cocktail> findAllWithPrix();

}