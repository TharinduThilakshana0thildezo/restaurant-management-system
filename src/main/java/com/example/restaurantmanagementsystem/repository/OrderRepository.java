package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    List<Order> findByTableNumber(String tableNumber);
}