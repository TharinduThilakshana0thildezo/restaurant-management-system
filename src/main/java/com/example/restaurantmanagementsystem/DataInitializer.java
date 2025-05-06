package com.example.restaurantmanagementsystem;

import com.example.restaurantmanagementsystem.Service.UserService;
import com.example.restaurantmanagementsystem.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword("admin123");
        admin.setRole("ADMIN");
        admin.setEmail("admin@example.com");
        userService.saveUser(admin);

        AppUser staff = new AppUser();
        staff.setUsername("staff");
        staff.setPassword("staff123");
        staff.setRole("STAFF");
        staff.setEmail("staff@example.com");
        userService.saveUser(staff);
    }
}