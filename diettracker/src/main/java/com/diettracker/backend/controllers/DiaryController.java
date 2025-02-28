package com.diettracker.backend.controllers;

import com.diettracker.backend.models.Diary;
import com.diettracker.backend.models.DiaryType;
import com.diettracker.backend.services.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return diaryService.getDiaryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Diary createDiary(@RequestBody Diary diary) {
        return diaryService.saveDiary(diary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diary> updateDiary(@PathVariable Long id, @RequestBody Diary diary) {
        return diaryService.updateDiary(id, diary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        return diaryService.deleteDiary(id);
    }

    @GetMapping("/search")
    public List<Diary> searchDiaries(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return diaryService.searchDiariesByName(name);
        }
        return diaryService.getAllDiaries();
    }

    @GetMapping("/type/{type}")
    public List<Diary> getDiariesByType(@PathVariable DiaryType type) {
        return diaryService.findByType(type);
    }
}