package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.User;
import com.example.demo.model.Category;
import com.example.demo.model.Product;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = getSampleCategories();
        model.addAttribute("categories", categories);
        List<Product> latestProducts = getSampleProducts();
        model.addAttribute("latestProducts", latestProducts.subList(0, Math.min(4, latestProducts.size())));
        return "index";
    }


    @GetMapping("/profile")
    public String profile(Model model) {
        // Sample user data
        User user = new User(1L, "John Doe", "john.doe@example.com", "+1234567890", "123 Main Street, City, State");
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "My Profile");
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User user, 
                               RedirectAttributes redirectAttributes) {
        
        System.out.println("Updating profile for: " + user.getName());
        redirectAttributes.addFlashAttribute("message", "Profile updated successfully!");
        return "redirect:/profile";
    }

    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmNewPassword,
                                RedirectAttributes redirectAttributes) {
        
        if (!newPassword.equals(confirmNewPassword)) {
            redirectAttributes.addFlashAttribute("error", "New passwords do not match!");
            return "redirect:/profile";
        }
        
        System.out.println("Changing password for user");
        redirectAttributes.addFlashAttribute("message", "Password changed successfully!");
        return "redirect:/profile";
    }
    
    @GetMapping("/products")
    public String products(@RequestParam(value = "search", required = false) String search,
                          @RequestParam(value = "category", required = false) String category,
                          Model model) {
        
        List<Product> products = getSampleProducts();

        // Filter by search term
        if (search != null && !search.trim().isEmpty()) {
            products = products.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                           p.getDescription().toLowerCase().contains(search.toLowerCase()))
                .toList();
            model.addAttribute("searchTerm", search);
        }
        
        // Filter by category
        if (category != null && !category.trim().isEmpty()) {
            products = products.stream()
                .filter(p -> p.getCategory().getTitle().toLowerCase().contains(category.toLowerCase()))
                .toList();
            model.addAttribute("selectedCategory", category);
        }
        
        model.addAttribute("products", products);
        model.addAttribute("categories", getSampleCategories());
        model.addAttribute("pageTitle", "Products");
        
        return "products";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("pageTitle", "Shopping Cart");
        model.addAttribute("cartItemCount", 3);
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, 
                           RedirectAttributes redirectAttributes) {
        
        System.out.println("Adding product " + productId + " to cart");
        redirectAttributes.addFlashAttribute("message", "Product added to cart successfully!");
        return "redirect:/products";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("pageTitle", "My Orders");
        return "orders";
    }

    @GetMapping("/error")
    public String error(@RequestParam(value = "status", required = false) Integer status,
                       @RequestParam(value = "message", required = false) String message,
                       Model model) {
        
        model.addAttribute("status", status != null ? status : 500);
        model.addAttribute("message", message != null ? message : "An unexpected error occurred");
        model.addAttribute("pageTitle", "Error");
        
        return "error";
    }

    private List<Category> getSampleCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Electronics", "Latest gadgets and electronic devices", "electronics.jpg"));
        categories.add(new Category(2L, "Clothing", "Fashion and apparel for everyone", "clothing.jpg"));
        categories.add(new Category(3L, "Books", "Educational and entertainment books", "books.jpg"));
        categories.add(new Category(4L, "Home & Garden", "Everything for your home and garden", "home-garden.jpg"));
        categories.add(new Category(5L, "Sports", "Sports equipment and fitness gear", "sports.jpg"));
        categories.add(new Category(6L, "Beauty", "Beauty and personal care products", "beauty.jpg"));
        return categories;
    }

    private List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();
        List<Category> categories = getSampleCategories();
        
        // Electronics
        products.add(new Product(1L, "Smartphone", "Latest Android smartphone with advanced features", 29999.0, 15, "smartphone.jpg", 15, categories.get(0)));
        products.add(new Product(2L, "Gaming Laptop", "High-performance laptop for gaming and work", 75999.0, 8, "laptop.jpg", 10, categories.get(0)));
        products.add(new Product(3L, "Wireless Headphones", "Premium noise-cancelling wireless headphones", 12999.0, 25, "headphones.jpg", 20, categories.get(0)));
        products.add(new Product(4L, "Smart Watch", "Fitness tracking smartwatch with GPS", 18999.0, 12, "smartwatch.jpg", 5, categories.get(0)));
        
        // Clothing
        products.add(new Product(5L, "Cotton T-Shirt", "Comfortable cotton t-shirt in various colors", 899.0, 50, "tshirt.jpg", 0, categories.get(1)));
        products.add(new Product(6L, "Denim Jeans", "Classic fit denim jeans for everyday wear", 2499.0, 30, "jeans.jpg", 25, categories.get(1)));
        products.add(new Product(7L, "Running Shoes", "Lightweight running shoes for athletes", 4999.0, 20, "shoes.jpg", 30, categories.get(1)));
        
        // Books
        products.add(new Product(8L, "Programming Guide", "Comprehensive guide to modern programming", 1299.0, 35, "programming-book.jpg", 0, categories.get(2)));
        products.add(new Product(9L, "Business Strategy", "Essential business strategy and management book", 1899.0, 15, "business-book.jpg", 0, categories.get(2)));
        
        // Home & Garden
        products.add(new Product(10L, "Coffee Maker", "Automatic coffee maker with timer", 8999.0, 10, "coffee-maker.jpg", 15, categories.get(3)));
        products.add(new Product(11L, "Plant Pot Set", "Decorative ceramic plant pots set of 3", 1599.0, 40, "plant-pots.jpg", 0, categories.get(3)));
        
        return products;
    }
}