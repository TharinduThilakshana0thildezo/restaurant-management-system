package com.ishan.restaurant_management_system.dashboard.service;



import com.ishan.restaurant_management_system.dashboard.model.MenuItem;
import com.ishan.restaurant_management_system.dashboard.model.OrderSummary;
import com.ishan.restaurant_management_system.dashboard.model.RevenueData;
import com.ishan.restaurant_management_system.dashboard.model.TableStatus;
import com.ishan.restaurant_management_system.dashboard.repository.MenuItemRepository;
import com.ishan.restaurant_management_system.dashboard.repository.OrderSummaryRepository;
import com.ishan.restaurant_management_system.dashboard.repository.RevenueRepository;
import com.ishan.restaurant_management_system.dashboard.repository.TableStatusRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final TableStatusRepository tableStatusRepository;
    private final OrderSummaryRepository orderSummaryRepository;
    private final RevenueRepository revenueRepository;
    private final MenuItemRepository menuItemRepository;

    
    public DashboardService(
            TableStatusRepository tableStatusRepository,
            OrderSummaryRepository orderSummaryRepository,
            RevenueRepository revenueRepository,
            MenuItemRepository menuItemRepository) {
        this.tableStatusRepository = tableStatusRepository;
        this.orderSummaryRepository = orderSummaryRepository;
        this.revenueRepository = revenueRepository;
        this.menuItemRepository = menuItemRepository;
    }

    
    public List<TableStatus> getAllTables() {
        return tableStatusRepository.findAll();
    }

    public Map<String, Long> getTableStatusCounts() {
        Map<String, Long> statusCounts = new HashMap<>();
        statusCounts.put("Available", tableStatusRepository.countByStatus(TableStatus.Status.Available));
        statusCounts.put("Occupied", tableStatusRepository.countByStatus(TableStatus.Status.Occupied));
        statusCounts.put("Reserved", tableStatusRepository.countByStatus(TableStatus.Status.Reserved));
        statusCounts.put("Cleaning", tableStatusRepository.countByStatus(TableStatus.Status.Cleaning));
        return statusCounts;
    }

    
    public List<OrderSummary> getRecentOrders() {
        return orderSummaryRepository.findTop5RecentOrders();
    }

    public long getTodaysOrderCount() {
        return orderSummaryRepository.countTodaysOrders();
    }

    public Map<String, Long> getOrderStatusCounts() {
        Map<String, Long> statusCounts = new HashMap<>();
        statusCounts.put("Pending", orderSummaryRepository.countByStatus(OrderSummary.Status.Pending));
        statusCounts.put("Preparing", orderSummaryRepository.countByStatus(OrderSummary.Status.Preparing));
        statusCounts.put("Ready", orderSummaryRepository.countByStatus(OrderSummary.Status.Ready));
        statusCounts.put("Served", orderSummaryRepository.countByStatus(OrderSummary.Status.Served));
        statusCounts.put("Paid", orderSummaryRepository.countByStatus(OrderSummary.Status.Paid));
        return statusCounts;
    }

    
    public List<RevenueData> getWeeklyRevenue() {
        LocalDate startDate = LocalDate.now().minusDays(6);
        return revenueRepository.findRevenueDataForLastDays(startDate);
    }

    public BigDecimal getTodaysRevenue() {
        BigDecimal revenue = revenueRepository.getTodaysTotalRevenue();
        return revenue != null ? revenue : BigDecimal.ZERO;
    }

    public BigDecimal getWeeklyTotalRevenue() {
        BigDecimal revenue = revenueRepository.getWeeklyRevenue();
        return revenue != null ? revenue : BigDecimal.ZERO;
    }

    
    public List<MenuItem> getPopularMenuItems() {
        return menuItemRepository.findTop5PopularItems();
    }

    
    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();
        
        
        summary.put("tableCounts", getTableStatusCounts());
        
        
        summary.put("todaysOrders", getTodaysOrderCount());
        summary.put("orderStatusCounts", getOrderStatusCounts());
        summary.put("recentOrders", getRecentOrders());
        
        
        summary.put("todaysRevenue", getTodaysRevenue());
        summary.put("weeklyRevenue", getWeeklyTotalRevenue());
        summary.put("revenueChart", getWeeklyRevenue());
        
        
        summary.put("popularItems", getPopularMenuItems());
        
        return summary;
    }
}
