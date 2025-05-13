package com.example.restaurantmanagementsystem.repository;


import com.example.restaurantmanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    List<Employee> findByStatus(String status);
    
    Optional<Employee> findByEmail(String email);
    
    List<Employee> findByPosition(String position);
    
    List<Employee> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
