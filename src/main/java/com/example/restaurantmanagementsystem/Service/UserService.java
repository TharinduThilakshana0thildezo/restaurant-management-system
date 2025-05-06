package com.example.restaurantmanagementsystem.Service;

import com.example.restaurantmanagementsystem.entity.AppUser;
import com.example.restaurantmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}