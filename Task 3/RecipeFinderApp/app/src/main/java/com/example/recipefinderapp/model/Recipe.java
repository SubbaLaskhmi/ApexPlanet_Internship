package com.example.recipefinderapp.model;

public class Recipe {
    private int id;
    private String title;
    private String image;
    private String summary; // Optional, if available
    private int readyInMinutes;
    private int servings;

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getImage() { return image; }
    public String getSummary() { return summary; }
    public int getReadyInMinutes() { return readyInMinutes; }
    public int getServings() { return servings; }
}

