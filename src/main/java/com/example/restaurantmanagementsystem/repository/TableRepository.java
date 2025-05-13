package com.example.restaurantmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.restaurantmanagementsystem.model.RestaurantTable;

import java.util.List;

public interface TableRepository extends JpaRepository<RestaurantTable, Integer> {
    List<RestaurantTable> findByStatus(String status);
    
    @Query("SELECT t FROM RestaurantTable t WHERE t.status = 'Available'")
    List<RestaurantTable> findAvailableTables();
}