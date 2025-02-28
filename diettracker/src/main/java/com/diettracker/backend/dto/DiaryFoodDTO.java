package com.diettracker.backend.dto;

import java.time.LocalDateTime;

public class DiaryFoodDTO {
    private final Long id;
    private final Long diaryId;
    private final Long foodId;
    private final String foodName;
    private final double weight;
    private final double calories;
    private final double proteins;
    private final double fats;
    private final double carbs;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public DiaryFoodDTO(
            Long id, Long diaryId, Long foodId, String foodName,
            double weight, double calories, double proteins,
            double fats, double carbs, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.diaryId = diaryId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public Long getId() { return id; }
    public Long getDiaryId() { return diaryId; }
    public Long getFoodId() { return foodId; }
    public String getFoodName() { return foodName; }
    public double getWeight() { return weight; }
    public double getCalories() { return calories; }
    public double getProteins() { return proteins; }
    public double getFats() { return fats; }
    public double getCarbs() { return carbs; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}