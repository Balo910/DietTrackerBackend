package com.diettracker.backend.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class DiaryWithFoodsDTO {
    private Long id;
    private LocalDate date;
    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiaryFoodDTO> diaryFoods;

    public DiaryWithFoodsDTO(Long id, LocalDate date, List<DiaryFoodDTO> foods) {
        this.id = id;
        this.date = date;
        this.diaryFoods = foods;
    }

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public List<DiaryFoodDTO> getDiaryFoods() { return diaryFoods; }
}