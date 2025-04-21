// DiaryController.java
package com.diettracker.backend.controllers;

import com.diettracker.backend.dto.DiaryFoodDTO;
import com.diettracker.backend.dto.DiaryWithFoodsDTO;
import com.diettracker.backend.models.Diary;
import com.diettracker.backend.models.DiaryFood;
import com.diettracker.backend.models.Food;
import com.diettracker.backend.repositories.DiaryFoodRepository;
import com.diettracker.backend.repositories.DiaryRepository;
import com.diettracker.backend.requests.AddDiaryFoodRequest;
import com.diettracker.backend.requests.CreateDiaryRequest;
import com.diettracker.backend.services.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryRepository diaryRepository;
    private final DiaryFoodRepository diaryFoodRepository;
    private final DiaryService diaryService;

    public DiaryController(DiaryRepository diaryRepository, DiaryFoodRepository diaryFoodRepository, DiaryService diaryService) {
        this.diaryRepository = diaryRepository;
        this.diaryFoodRepository = diaryFoodRepository;
        this.diaryService = diaryService;
    }

    @GetMapping
    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diary> getDiaryById(@PathVariable Long id) {
        Optional<Diary> diary = diaryRepository.findById(id);
        return diary.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Diary> createDiary(@RequestBody CreateDiaryRequest request) {
        Diary diary = new Diary();
        diary.setDate((LocalDate.now()));

        Diary savedDiary = diaryRepository.save(diary);
        return ResponseEntity.ok(savedDiary);
    }

    @GetMapping("/with-foods")
    public List<DiaryWithFoodsDTO> getAllDiariesWithFoods() {
        List<Diary> diaries = diaryRepository.findAll();

        return diaries.stream().map(diary -> {
            List<DiaryFoodDTO> foodDTOs = diary.getDiaryFoods().stream()
                    .map(this::convertToDTO)
                    .toList();
            return new DiaryWithFoodsDTO(diary.getId(), diary.getDate(), foodDTOs);
        }).toList();
    }

    @PostMapping
    public ResponseEntity<DiaryFoodDTO> addFoodToDiary(@RequestBody AddDiaryFoodRequest request) {
        DiaryFood diaryFood = diaryService.addFoodToDiary(
                request.getDiaryId(),
                request.getFoodId(),
                request.getWeight()
        );
        return ResponseEntity.ok(convertToDTO(diaryFood));
    }

    private DiaryFoodDTO convertToDTO(DiaryFood diaryFood) {
        Food food = diaryFood.getFood();
        double weightRatio = diaryFood.getWeight() / food.getWeight();

        return new DiaryFoodDTO(
                diaryFood.getId(),
                diaryFood.getDiary().getId(),
                food.getId(),
                food.getName(),
                diaryFood.getWeight(),
                Math.round(food.getCalories() * weightRatio * 10.0) / 10.0,
                Math.round(food.getProteins() * weightRatio * 10.0) / 10.0,
                Math.round(food.getFats() * weightRatio * 10.0) / 10.0,
                Math.round(food.getCarbs() * weightRatio * 10.0) / 10.0,
                diaryFood.getCreatedAt(),
                diaryFood.getUpdatedAt()
        );
    }
}
