package com.diettracker.backend.repositories;

import com.diettracker.backend.models.DiaryFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DiaryFoodRepository extends JpaRepository<DiaryFood, Long> {
    Optional<DiaryFood> findByDiaryIdAndFoodId(Long diaryId, Long foodId);
}