package com.diettracker.backend.diaryfluid;

public class UpdateDiaryFluidRequest {
    private double volume;
    private String fluidName;
    private double calories;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getFluidName() {
        return fluidName;
    }

    public void setFluidName(String fluidName) {
        this.fluidName = fluidName;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    // getters and setters
}