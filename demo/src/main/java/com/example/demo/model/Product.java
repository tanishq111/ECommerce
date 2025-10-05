package com.example.demo.model;

public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Integer stock;
    private String image;
    private Integer discount = 0;
    private Double discountPrice;
    private Boolean isActive = true;
    private Category category;
    
    // Constructors
    public Product() {}
    
    public Product(Long id, String title, String description, Double price, Integer stock, String image, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category = category;
        this.discountPrice = price;
    }
    
    public Product(Long id, String title, String description, Double price, Integer stock, String image, Integer discount, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.discount = discount;
        this.category = category;
        // Calculate discount price
        if (discount > 0) {
            this.discountPrice = price - (price * discount / 100);
        } else {
            this.discountPrice = price;
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public Integer getDiscount() {
        return discount;
    }
    
    public void setDiscount(Integer discount) {
        this.discount = discount;
        // Recalculate discount price
        if (discount > 0 && price != null) {
            this.discountPrice = price - (price * discount / 100);
        } else {
            this.discountPrice = price;
        }
    }
    
    public Double getDiscountPrice() {
        return discountPrice;
    }
    
    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
}
