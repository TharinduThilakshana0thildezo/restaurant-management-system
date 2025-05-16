package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByQuantityLessThan(int quantity);
}
