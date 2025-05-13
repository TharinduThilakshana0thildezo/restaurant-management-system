package com.example.restaurantmanagementsystem.table.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restaurantmanagementsystem.Service.TableService;
import com.example.restaurantmanagementsystem.model.RestaurantTable;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public List<RestaurantTable> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTable> getTableById(@PathVariable Integer id) {
        RestaurantTable table = tableService.getTableById(id);
        return table != null ? ResponseEntity.ok(table) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public RestaurantTable createTable(@RequestBody RestaurantTable table) {
        return tableService.createTable(table);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTable> updateTable(
            @PathVariable Integer id, 
            @RequestBody RestaurantTable tableDetails) {
        RestaurantTable updatedTable = tableService.updateTable(id, tableDetails);
        return updatedTable != null ? ResponseEntity.ok(updatedTable) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Integer id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<RestaurantTable> getTablesByStatus(@PathVariable String status) {
        return tableService.getTablesByStatus(status);
    }

    @GetMapping("/available")
    public List<RestaurantTable> getAvailableTables() {
        return tableService.getAvailableTables();
    }
}