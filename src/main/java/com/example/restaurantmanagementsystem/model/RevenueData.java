package com.example.restaurantmanagementsystem.model;



import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "daily_revenue")
public class RevenueData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "total_revenue")
    private BigDecimal totalRevenue;

    @Column(name = "total_orders")
    private Integer totalOrders;

    @Column(name = "avg_order_value", insertable = false, updatable = false)
    private BigDecimal avgOrderValue;

    
    public RevenueData() {
    }

    
    public RevenueData(LocalDate date, BigDecimal totalRevenue, Integer totalOrders) {
        this.date = date;
        this.totalRevenue = totalRevenue;
        this.totalOrders = totalOrders;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getAvgOrderValue() {
        return avgOrderValue;
    }

    @Override
    public String toString() {
        return "RevenueData{" +
                "id=" + id +
                ", date=" + date +
                ", totalRevenue=" + totalRevenue +
                ", totalOrders=" + totalOrders +
                ", avgOrderValue=" + avgOrderValue +
                '}';
    }
}
