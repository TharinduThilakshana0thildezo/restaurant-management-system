package com.example.restaurantmanagementsystem.repository;


import com.example.restaurantmanagementsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderId(Long orderId);  // Custom query method
}