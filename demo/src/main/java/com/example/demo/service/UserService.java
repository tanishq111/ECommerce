package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import java.util.Optional;

/**
 * Service for user management operations
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register a new user with CUSTOMER role by default
     */
    public User registerUser(User user) {

        System.out.println("Registering user: " + user.getEmail());
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Assign default CUSTOMER role
        Role customerRole = roleRepository.findByName("User")
                .orElseThrow(() -> new RuntimeException("Default role CUSTOMER not found"));
        user.addRole(customerRole);
        
        return userRepository.save(user);
    }

    /**
     * Register an admin user
     */
    public User registerAdmin(User user) {
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Assign ADMIN role
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Admin role not found"));
        user.addRole(adminRole);
        
        return userRepository.save(user);
    }

    /**
     * Find user by email
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Check if email already exists
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Save or update user
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Initialize default roles if they don't exist
     */
    @Transactional
    public void initializeRoles() {
        if (!roleRepository.existsByName("ADMIN")) {
            Role adminRole = new Role("ADMIN", "Administrator role with full access");
            roleRepository.save(adminRole);
        }
        
        if (!roleRepository.existsByName("USER")) {
            Role userRole = new Role("USER", "User role with limited access");
            roleRepository.save(userRole);
        }
    }
} 

