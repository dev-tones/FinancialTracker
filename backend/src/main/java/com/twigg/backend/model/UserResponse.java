package com.twigg.backend.model;

public class UserResponse {
    private String name;
    private String role;
    private int yearsExperience;

    public UserResponse(String name, String role, int yearsExperience) {
        this.name = name;
        this.role = role;
        this.yearsExperience = yearsExperience;
    }
    // Getters and setters
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public int getYearsExperience() {
        return yearsExperience;
    }
    
}
