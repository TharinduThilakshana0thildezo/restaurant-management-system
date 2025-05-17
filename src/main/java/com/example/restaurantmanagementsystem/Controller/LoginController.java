package com.example.restaurantmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/password-recovery")
    public String passwordRecovery() {
        return "password-recovery";
    }
} 