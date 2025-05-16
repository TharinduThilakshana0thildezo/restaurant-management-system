package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}

