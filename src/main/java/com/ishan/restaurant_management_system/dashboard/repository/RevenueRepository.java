package com.ishan.restaurant_management_system.dashboard.repository;



import com.ishan.restaurant_management_system.dashboard.model.RevenueData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<RevenueData, Integer> {
    
    RevenueData findByDate(LocalDate date);
    
    @Query("SELECT r FROM RevenueData r WHERE r.date >= :startDate ORDER BY r.date")
    List<RevenueData> findRevenueDataForLastDays(LocalDate startDate);
    
    @Query(value = "SELECT SUM(total_revenue) FROM daily_revenue WHERE date = CURDATE()", nativeQuery = true)
    BigDecimal getTodaysTotalRevenue();
    
    @Query(value = "SELECT SUM(total_revenue) FROM daily_revenue WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()", nativeQuery = true)
    BigDecimal getWeeklyRevenue();
}