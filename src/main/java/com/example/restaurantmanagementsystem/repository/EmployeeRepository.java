package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    List<Employee> findByPosition(String position);

    List<Employee> findByStatus(String status);
}
