package com.example.restaurantmanagementsystem.repository;




import com.example.restaurantmanagementsystem.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    
    List<MenuItem> findByCategory(MenuItem.Category category);
    
    @Query("SELECT m FROM MenuItem m WHERE m.isAvailable = true ORDER BY m.timesOrdered DESC LIMIT 5")
    List<MenuItem> findTop5PopularItems();
    
    List<MenuItem> findByIsAvailable(Boolean isAvailable);
}