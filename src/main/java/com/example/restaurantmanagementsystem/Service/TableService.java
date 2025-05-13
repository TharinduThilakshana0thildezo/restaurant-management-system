package com.example.restaurantmanagementsystem.Service;

import com.example.restaurantmanagementsystem.model.RestaurantTable;
import com.example.restaurantmanagementsystem.repository.TableRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TableService {
    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<RestaurantTable> getAllTables() {
        return tableRepository.findAll();
    }

    public RestaurantTable getTableById(Integer id) {
        return tableRepository.findById(id).orElse(null);
    }

    public RestaurantTable createTable(RestaurantTable table) {
        return tableRepository.save(table);
    }

    public RestaurantTable updateTable(Integer id, RestaurantTable tableDetails) {
        RestaurantTable table = tableRepository.findById(id).orElse(null);
        if (table != null) {
            table.setTableNumber(tableDetails.getTableNumber());
            table.setSeats(tableDetails.getSeats());
            table.setStatus(tableDetails.getStatus());
            table.setAssignedWaiterId(tableDetails.getAssignedWaiterId());
            return tableRepository.save(table);
        }
        return null;
    }

    public void deleteTable(Integer id) {
        tableRepository.deleteById(id);
    }

    public List<RestaurantTable> getTablesByStatus(String status) {
        return tableRepository.findByStatus(status);
    }

    public List<RestaurantTable> getAvailableTables() {
        return tableRepository.findAvailableTables();
    }
}