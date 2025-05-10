package com.ishan.restaurant_management_system.dashboard.controller;



import com.ishan.restaurant_management_system.dashboard.model.MenuItem;
import com.ishan.restaurant_management_system.dashboard.model.OrderSummary;
import com.ishan.restaurant_management_system.dashboard.model.RevenueData;
import com.ishan.restaurant_management_system.dashboard.model.TableStatus;
import com.ishan.restaurant_management_system.dashboard.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String redirectToDashboard() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Map<String, Object> dashboardData = dashboardService.getDashboardSummary();
        model.addAllAttributes(dashboardData);
        return "dashboard";
    }

    @GetMapping("/api/tables")
    @ResponseBody
    public List<TableStatus> getTables() {
        return dashboardService.getAllTables();
    }

    @GetMapping("/api/orders/recent")
    @ResponseBody
    public List<OrderSummary> getRecentOrders() {
        return dashboardService.getRecentOrders();
    }

    @GetMapping("/api/revenue/weekly")
    @ResponseBody
    public List<RevenueData> getWeeklyRevenue() {
        return dashboardService.getWeeklyRevenue();
    }

    @GetMapping("/api/menu/popular")
    @ResponseBody
    public List<MenuItem> getPopularItems() {
        return dashboardService.getPopularMenuItems();
    }

    @GetMapping("/api/dashboard/summary")
    @ResponseBody
    public Map<String, Object> getDashboardSummary() {
        return dashboardService.getDashboardSummary();
    }
}
