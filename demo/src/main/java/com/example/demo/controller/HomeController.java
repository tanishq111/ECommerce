package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
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