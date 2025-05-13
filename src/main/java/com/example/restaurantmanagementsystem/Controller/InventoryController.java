package com.example.restaurantmanagementsystem.Controller;

import ch.qos.logback.core.model.Model;
import com.example.restaurantmanagementsystem.model.Ingredient;
import com.example.restaurantmanagementsystem.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController  // Defines it as a REST API Controller
@RequestMapping("/inventory")  // Base URL for this controller
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
        return "inventory";
    }
}
