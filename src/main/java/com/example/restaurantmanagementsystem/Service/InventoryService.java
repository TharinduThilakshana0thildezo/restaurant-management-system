package com.example.restaurantmanagementsystem.Service;

import com.example.restaurantmanagementsystem.model.Ingredient;
import com.example.restaurantmanagementsystem.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service  // Marks it as a service component
public class InventoryService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
    public List<Ingredient> checkLowStock() {
        return ingredientRepository.findByQuantityLessThan(5);
    }
}

