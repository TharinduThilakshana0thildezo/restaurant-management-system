package com.example.restaurantmanagementsystem.model;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<MenuItem.Category, String> {

    
    @Override
    public String convertToDatabaseColumn(MenuItem.Category category) {
        if (category == null) {
            return null;
        }
        return category.getValue();
    }

    
    @Override
    public MenuItem.Category convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        
        try {
            return MenuItem.Category.fromValue(dbData);
        } catch (IllegalArgumentException e) {
            
            System.err.println("Unknown category value in database: " + dbData);
            return null; 
        }
    }
}