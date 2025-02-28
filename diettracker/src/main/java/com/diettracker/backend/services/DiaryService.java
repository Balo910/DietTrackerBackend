package com.diettracker.backend.services;

import com.diettracker.backend.models.Diary;
import com.diettracker.backend.models.DiaryType;
import com.diettracker.backend.repositories.DiaryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
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

    public ResponseEntity<Diary> updateDiary(Long id, Diary updatedDiary) {
        return diaryRepository.findById(id)
                .map(diary -> {
                    diary.setType(updatedDiary.getType());
                    diary.setName(updatedDiary.getName());
                    return ResponseEntity.ok(diaryRepository.save(diary));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteDiary(Long id) {
        if (!diaryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        diaryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public List<Diary> searchDiariesByName(String name) {
        return diaryRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Diary> findByType(DiaryType type) {
        return diaryRepository.findByType(type);
    }
}
