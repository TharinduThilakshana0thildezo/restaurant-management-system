package com.ishan.restaurant_management_system.dashboard.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "times_ordered", nullable = false)
    private Integer timesOrdered = 0;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    
    public enum Category {
        Appetizer("Appetizer"),
        MainCourse("Main Course"),
        Dessert("Dessert"),
        Beverage("Beverage");

        private final String value;

        Category(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Category fromValue(String value) {
            for (Category category : Category.values()) {
                if (category.value.equalsIgnoreCase(value)) {
                    return category;
                }
            }
            throw new IllegalArgumentException("Unknown category: " + value);
        }
    }

   
    public MenuItem() {}

    public MenuItem(String name, Category category, BigDecimal price, Integer timesOrdered, Boolean isAvailable) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.timesOrdered = timesOrdered;
        this.isAvailable = isAvailable;
    }

  
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(Integer timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", timesOrdered=" + timesOrdered +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
