package com.example.restaurantmanagementsystem.Controller;

import com.example.restaurantmanagementsystem.Service.UserService;
import com.example.restaurantmanagementsystem.entity.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = userService.findByUsername(username);
        model.addAttribute("user", appUser);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam String email, @RequestParam String username) {
        AppUser appUser = userService.findByUsername(username);
        if (appUser != null) {
            appUser.setEmail(email);
            userService.saveUser(appUser);
        }
        return "redirect:/profile";
    }

    @GetMapping("/password-recovery")
    public String passwordRecoveryPage() {
        return "password-recovery";
    }
}