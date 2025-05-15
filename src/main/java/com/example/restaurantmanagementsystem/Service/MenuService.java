package com.example.restaurantmanagementsystem.Service;

import com.example.restaurantmanagementsystem.model.MenuItem;
import com.example.restaurantmanagementsystem.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    
    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }
    
    public MenuItem getMenuItemById(Integer id) {
        return menuRepository.findById(id).orElse(null);
    }
    
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }
    
    public MenuItem updateMenuItem(Integer id, MenuItem menuItemDetails) {
        MenuItem menuItem = menuRepository.findById(id).orElse(null);
        if (menuItem != null) {
            menuItem.setName(menuItemDetails.getName());
            menuItem.setDescription(menuItemDetails.getDescription());
            menuItem.setPrice(menuItemDetails.getPrice());
            menuItem.setCategory(menuItemDetails.getCategory());
            menuItem.setIsAvailable(menuItemDetails.getIsAvailable());
            menuItem.setPreparationTime(menuItemDetails.getPreparationTime());
            return menuRepository.save(menuItem);
        }
        return null;
    }
    
    public void deleteMenuItem(Integer id) {
        menuRepository.deleteById(id);
    }
    
    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuRepository.findByCategory(category);
    }
    
    public List<MenuItem> getAvailableMenuItems() {
        return menuRepository.findByIsAvailable(true);
    }
}