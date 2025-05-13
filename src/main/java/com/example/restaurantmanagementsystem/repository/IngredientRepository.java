package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository  // Marks it as a repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}


