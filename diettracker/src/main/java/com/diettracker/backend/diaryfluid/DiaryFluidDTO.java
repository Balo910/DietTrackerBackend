package com.diettracker.backend.diaryfluid;

import java.time.LocalDateTime;

public class DiaryFluidDTO {
    private final Long id;
    private final Long diaryId;
    private final Long fluidId;
    private final String fluidName;
    private final double volume;
    private final double calories;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public DiaryFluidDTO(
            Long id, Long diaryId, Long fluidId, String fluidName,
            double volume, double calories,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.diaryId = diaryId;
        this.fluidId = fluidId;
        this.fluidName = fluidName;
        this.volume = volume;
        this.calories = calories;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public Long getDiaryId() { return diaryId; }
    public Long getFluidId() { return fluidId; }
    public String getFluidName() { return fluidName; }
    public double getVolume() { return volume; }
    public double getCalories() { return calories; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
