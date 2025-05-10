package com.ishan.restaurant_management_system.dashboard.repository;


import com.ishan.restaurant_management_system.dashboard.model.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSummaryRepository extends JpaRepository<OrderSummary, Integer> {
    
    List<OrderSummary> findByStatus(OrderSummary.Status status);
    
    @Query("SELECT o FROM OrderSummary o ORDER BY o.orderTime DESC LIMIT 5")
    List<OrderSummary> findTop5RecentOrders();
    
    @Query("SELECT COUNT(o) FROM OrderSummary o WHERE o.status = :status")
    long countByStatus(OrderSummary.Status status);
    
    @Query(value = "SELECT COUNT(*) FROM orders WHERE DATE(order_time) = CURDATE()", nativeQuery = true)
    long countTodaysOrders();
}
