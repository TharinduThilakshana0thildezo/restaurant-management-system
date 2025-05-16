package com.example.restaurantmanagementsystem.model;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @NotBlank(message = "Ingredient name is required")
    private String name;
    // @Min(value = 1, message = "Quantity should be at least 1")
    private int quantity;
    private String unit;
    private int reorderLevel;

    // Getters and Setters
    public Long getId() {
        return id;}
    public void setId(Long id) {
        this.id = id;}
    public String getName() {
        return name;}
    public void setName(String name) {
        this.name = name;}
    public int getQuantity() {
        return quantity;}
    public void setQuantity(int quantity) {
        this.quantity = quantity;}
    public String getUnit() {
        return unit;}
    public void setUnit(String unit) {
        this.unit = unit;}
    public int getReorderLevel() {
        return reorderLevel;}
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;}
}

