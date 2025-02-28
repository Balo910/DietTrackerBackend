package com.diettracker.backend.repositories;

import com.diettracker.backend.models.DiaryFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryFoodRepository extends JpaRepository<DiaryFood, Long> {
    List<DiaryFood> findByDiaryId(Long diaryId);
    List<DiaryFood> findByFoodNameContainingIgnoreCase(String name);
}