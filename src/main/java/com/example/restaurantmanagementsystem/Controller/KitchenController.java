package com.example.restaurantmanagementsystem.Controller;



import com.example.restaurantmanagementsystem.Service.KitchenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kitchen")
public class KitchenController {

    private final KitchenService kitchenService;

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping
    public String getKitchenOrders(Model model) {
        model.addAttribute("orders", kitchenService.getPendingOrders());
        model.addAttribute("now", java.time.LocalDateTime.now());
        return "kitchen-display";
    }

    @PostMapping("/update/{id}")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        kitchenService.updateOrderStatus(id, status);
        return "redirect:/kitchen";
    }
}


