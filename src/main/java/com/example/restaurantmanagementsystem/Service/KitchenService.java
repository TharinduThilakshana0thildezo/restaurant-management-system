// src/main/java/com/example/restaurantmanagementsystem/service/KitchenService.java
package com.example.restaurantmanagementsystem.Service;

import com.example.restaurantmanagementsystem.model.Order;
import com.example.restaurantmanagementsystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {

    private final OrderRepository orderRepo;

    public KitchenService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getPendingOrders() {
        return orderRepo.findByStatusOrderByOrderDateAsc("NEW"); // matches repo signature
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Order " + orderId + " not found"));
        order.setStatus(status);
        orderRepo.save(order);
    }
}
