package com.example.restaurantmanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private Boolean isAvailable = true;
    
    @Column(nullable = false)
    private Integer preparationTime = 0;
    
    public enum Category {
        APPETIZER("appetizer"),
        MAIN_COURSE("main_course"),
        DESSERT("dessert"),
        BEVERAGE("beverage"),
        SIDE_DISH("side_dish");
        
        private final String value;
        
        Category(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        public static Category fromValue(String value) {
            for (Category category : Category.values()) {
                if (category.getValue().equals(value)) {
                    return category;
                }
            }
            throw new IllegalArgumentException("Unknown category: " + value);
        }
    }
    
    public MenuItem() {
    }
    
    public MenuItem(String name, BigDecimal price, Category category, String description, Boolean isAvailable, Integer preparationTime) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.isAvailable = isAvailable;
        this.preparationTime = preparationTime;
    }
    
 
    public MenuItem(String name, BigDecimal price, Category category, String description) {
        this(name, price, category, description, true, 0);
    }
    

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getIsAvailable() {
        return isAvailable;
    }
    
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public Integer getPreparationTime() {
        return preparationTime;
    }
    
    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }
    
    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                ", preparationTime=" + preparationTime +
                '}';
    }
}