
package com.example.restaurantmanagementsystem.Controller;

import com.example.restaurantmanagementsystem.model.Ingredient;
import com.example.restaurantmanagementsystem.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/levels")
    public List<Ingredient> getInventoryLevels() {
        return inventoryService.getAllIngredients();
    }

    @PostMapping("/ingredient/add")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return inventoryService.saveIngredient(ingredient);
    }

    @GetMapping("/low-stock")
    public List<Ingredient> getLowStockAlerts() {
        return inventoryService.checkLowStock();
    }

    @GetMapping("/dashboard")
    public String inventoryDashboard(Model model) {
        model.addAttribute("ingredients", inventoryService.getAllIngredients());
        return "inventory"; // Assumes there is a Thymeleaf template named inventory.html
    }
}
