package com.diettracker.backend.services;

import com.diettracker.backend.models.*;
import com.diettracker.backend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FluidRepository fluidRepository;

    @Autowired
    private DiaryFoodRepository diaryFoodRepository;

    @Autowired
    private DiaryFluidRepository diaryFluidRepository;

    @Transactional
    public DiaryFood addFoodToDiary(Long diaryId, Long foodId, double weight) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new EntityNotFoundException("Diary not found with id: " + diaryId));
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new EntityNotFoundException("Food not found with id: " + foodId));
        DiaryFood diaryFood = new DiaryFood(diary, food, weight);
        return diaryFoodRepository.save(diaryFood);
    }

    @Transactional
    public DiaryFluid addFluidToDiary(Long diaryId, Long fluidId, double volume) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new EntityNotFoundException("Diary not found with id: " + diaryId));
        Fluid fluid = fluidRepository.findById(fluidId)
                .orElseThrow(() -> new EntityNotFoundException("Fluid not found with id: " + fluidId));
        DiaryFluid diaryFluid = new DiaryFluid(diary, fluid, volume);
        return diaryFluidRepository.save(diaryFluid);
    }

    @Transactional
    public void deleteDiaryFood(Long diaryId, Long foodId) {
        DiaryFood diaryFood = diaryFoodRepository.findByDiaryIdAndFoodId(diaryId, foodId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DiaryFood entry not found for diary id: " + diaryId + " and food id: " + foodId));
        diaryFoodRepository.delete(diaryFood);
    }

    @Transactional
    public void deleteDiaryFluid(Long diaryId, Long fluidId) {
        DiaryFluid diaryFluid = diaryFluidRepository.findByDiaryIdAndFluidId(diaryId, fluidId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DiaryFluid entry not found for diary id: " + diaryId + " and fluid id: " + fluidId));
        diaryFluidRepository.delete(diaryFluid);
    }




}