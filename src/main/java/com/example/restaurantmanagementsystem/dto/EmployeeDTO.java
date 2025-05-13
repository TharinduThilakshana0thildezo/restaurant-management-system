package com.example.restaurantmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    
    private Long id;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    
    private String phone;
    
    @NotBlank(message = "Position is required")
    private String position;
    
    @NotNull(message = "Hire date is required")
    @Past(message = "Hire date must be in the past")
    private LocalDate hireDate;
    
    private BigDecimal salary;
    
    private String address;
    
    private String emergencyContact;
    
    private String status;
}
