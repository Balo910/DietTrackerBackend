package com.diettracker.backend.services;

import com.diettracker.backend.models.*;
import com.diettracker.backend.repositories.DiaryFoodRepository;
import com.diettracker.backend.repositories.DiaryFluidRepository;
import com.diettracker.backend.repositories.DiaryRepository;
import com.diettracker.backend.repositories.FoodRepository;
import com.diettracker.backend.repositories.FluidRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryFoodRepository diaryFoodRepository;
    private final DiaryFluidRepository diaryFluidRepository;
    private final FoodRepository foodRepository;
    private final FluidRepository fluidRepository;

    public DiaryService(
            DiaryRepository diaryRepository,
            DiaryFoodRepository diaryFoodRepository,
            DiaryFluidRepository diaryFluidRepository,
            FoodRepository foodRepository,
            FluidRepository fluidRepository) {
        this.diaryRepository = diaryRepository;
        this.diaryFoodRepository = diaryFoodRepository;
        this.diaryFluidRepository = diaryFluidRepository;
        this.foodRepository = foodRepository;
        this.fluidRepository = fluidRepository;
    }

    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    public Optional<Diary> getDiaryById(Long id) {
        return diaryRepository.findById(id);
    }

    public Diary saveDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    public Optional<Diary> updateDiary(Long id, Diary updatedDiary) {
        return diaryRepository.findById(id)
                .map(diary -> {
                    diary.setDate(updatedDiary.getDate());
                    return diaryRepository.save(diary);
                });
    }

    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

    public DiaryFood addFoodToDiary(Long diaryId, Long foodId, double weight) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        Food food = foodRepository.findById(foodId).orElseThrow();
        DiaryFood diaryFood = new DiaryFood(diary, food, weight);
        return diaryFoodRepository.save(diaryFood);
    }

    public DiaryFluid addFluidToDiary(Long diaryId, Long fluidId, double volume) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        Fluid fluid = fluidRepository.findById(fluidId).orElseThrow();
        DiaryFluid diaryFluid = new DiaryFluid(diary, fluid, volume);
        return diaryFluidRepository.save(diaryFluid);
    }
}