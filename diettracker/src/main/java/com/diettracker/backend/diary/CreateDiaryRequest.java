package com.diettracker.backend.diary;

import java.time.LocalDate;

public class CreateDiaryRequest {
    private LocalDate date;
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}