package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.initializeRoles();
        
        // Create default admin user if doesn't exist
        if (!userService.existsByEmail("admin@ecommerce.com")) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@ecommerce.com");
            admin.setPassword("admin123"); // This will be encoded
            admin.setPhone("1234567890");
            admin.setAddress("Admin Address");
            
            userService.registerAdmin(admin);
            System.out.println("Default admin user created:");
            System.out.println("Email: admin@ecommerce.com");
            System.out.println("Password: admin123");
        }
    }
}
