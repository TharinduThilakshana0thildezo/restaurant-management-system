package com.example.restaurantmanagementsystem.Service;

import com.example.restaurantmanagementsystem.model.Order;
import com.example.restaurantmanagementsystem.model.OrderItem;
import com.example.restaurantmanagementsystem.repository.OrderRepository;
import com.example.restaurantmanagementsystem.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        calculateTotalAmount(order);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setTableNumber(orderDetails.getTableNumber());
            order.setCustomerName(orderDetails.getCustomerName());
            order.setStatus(orderDetails.getStatus());
            order.setNotes(orderDetails.getNotes());
            calculateTotalAmount(order);
            return orderRepository.save(order);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByTable(String tableNumber) {
        return orderRepository.findByTableNumber(tableNumber);
    }

    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    private void calculateTotalAmount(Order order) {
        if (order.getItems() != null) {
            BigDecimal total = order.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalAmount(total);
        }
    }
}