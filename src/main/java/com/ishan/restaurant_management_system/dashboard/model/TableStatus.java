package com.ishan.restaurant_management_system.dashboard.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tables")
public class TableStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tableId;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    
    public enum Status {
        Available, Occupied, Reserved, Cleaning
    }

    
    public TableStatus() {
    }

    
    public TableStatus(Integer tableNumber, Integer capacity, Status status) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = status;
        this.lastUpdated = LocalDateTime.now();
    }

    
    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "TableStatus{" +
                "tableId=" + tableId +
                ", tableNumber=" + tableNumber +
                ", capacity=" + capacity +
                ", status=" + status +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}