package com.diettracker.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class DiaryWithFoodsAndFluidsDTO {
    private Long id;
    private LocalDate date;
    private List<DiaryFoodDTO> diaryFoods;
    private List<DiaryFluidDTO> diaryFluids;

    public DiaryWithFoodsAndFluidsDTO(Long id, LocalDate date, List<DiaryFoodDTO> foods, List<DiaryFluidDTO> fluids) {
        this.id = id;
        this.date = date;
        this.diaryFoods = foods;
        this.diaryFluids = fluids;
    }

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public List<DiaryFoodDTO> getDiaryFoods() { return diaryFoods; }
    public List<DiaryFluidDTO> getDiaryFluids() { return diaryFluids; }
}
