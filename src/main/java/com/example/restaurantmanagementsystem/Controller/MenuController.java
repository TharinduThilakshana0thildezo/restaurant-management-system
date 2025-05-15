package com.example.restaurantmanagementsystem.Controller;



import com.example.restaurantmanagementsystem.model.MenuItem;
import com.example.restaurantmanagementsystem.Service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Integer id) {
        MenuItem menuItem = menuService.getMenuItemById(id);
        return menuItem != null ? ResponseEntity.ok(menuItem) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuService.createMenuItem(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable Integer id, 
            @RequestBody MenuItem menuItemDetails) {
        MenuItem updatedMenuItem = menuService.updateMenuItem(id, menuItemDetails);
        return updatedMenuItem != null ? ResponseEntity.ok(updatedMenuItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Integer id) {
        menuService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable String category) {
        return menuService.getMenuItemsByCategory(category);
    }

    @GetMapping("/available")
    public List<MenuItem> getAvailableMenuItems() {
        return menuService.getAvailableMenuItems();
    }
}
