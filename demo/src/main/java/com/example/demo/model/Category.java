package com.example.demo.model;

public class Category {
    private Long id;
    private String title;
    private String description;
    private String imageName;
    private Boolean isActive = true;
    
    public Category() {}
    
    public Category(Long id, String title, String description, String imageName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageName = imageName;
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
    
    public String getImageName() {
        return imageName;
    }
    
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
