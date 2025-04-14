package com.diettracker.backend.controllers;

import com.diettracker.backend.models.*;
import com.diettracker.backend.services.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    public List<Diary> getAllDiaries() {
        return diaryService.getAllDiaries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diary> getDiaryById(@PathVariable Long id) {
        Optional<Diary> diary = diaryService.getDiaryById(id);
        return diary.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Diary createDiary(@RequestBody Diary diary) {
        return diaryService.saveDiary(diary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diary> updateDiary(@PathVariable Long id, @RequestBody Diary updatedDiary) {
        Optional<Diary> diary = diaryService.updateDiary(id, updatedDiary);
        return diary.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{diaryId}/food")
    public DiaryFood addFoodToDiary(@PathVariable Long diaryId, @RequestParam Long foodId, @RequestParam double weight) {
        return diaryService.addFoodToDiary(diaryId, foodId, weight);
    }

    @PostMapping("/{diaryId}/fluid")
    public DiaryFluid addFluidToDiary(@PathVariable Long diaryId, @RequestParam Long fluidId, @RequestParam double volume) {
        return diaryService.addFluidToDiary(diaryId, fluidId, volume);
    }
}