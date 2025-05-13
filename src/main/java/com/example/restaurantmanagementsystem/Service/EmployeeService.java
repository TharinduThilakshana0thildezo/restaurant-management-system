package com.example.restaurantmanagementsystem.Service;


import com.example.restaurantmanagementsystem.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {
    
    List<EmployeeDTO> getAllEmployees();
    
    EmployeeDTO getEmployeeById(Long id);
    
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    
    void deleteEmployee(Long id);
    
    List<EmployeeDTO> searchEmployees(String query);
    
    List<EmployeeDTO> getEmployeesByPosition(String position);
    
    List<EmployeeDTO> getActiveEmployees();
}