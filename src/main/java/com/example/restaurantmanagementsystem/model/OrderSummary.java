package com.example.restaurantmanagementsystem.model;



import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_summaries")
public class OrderSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableStatus table;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    // Enum for order status
    public enum Status {
        Pending, Preparing, Ready, Served, Paid
    }

    
    public OrderSummary() {
    }

    
    public OrderSummary(TableStatus table, String customerName, Status status, BigDecimal totalAmount) {
        this.table = table;
        this.customerName = customerName;
        this.orderTime = LocalDateTime.now();
        this.status = status;
        this.totalAmount = totalAmount;
    }

    
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public TableStatus getTable() {
        return table;
    }

    public void setTable(TableStatus table) {
        this.table = table;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderSummary{" +
                "orderId=" + orderId +
                ", table=" + (table != null ? table.getTableNumber() : null) +
                ", customerName='" + customerName + '\'' +
                ", orderTime=" + orderTime +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                '}';
    }
}