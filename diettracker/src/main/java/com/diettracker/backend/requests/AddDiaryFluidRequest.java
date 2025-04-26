package com.diettracker.backend.requests;

public class AddDiaryFluidRequest {

    private Long diaryId;
    private Long fluidId;
    private double volume;

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public Long getFluidId() {
        return fluidId;
    }

    public void setFluidId(Long fluidId) {
        this.fluidId = fluidId;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
