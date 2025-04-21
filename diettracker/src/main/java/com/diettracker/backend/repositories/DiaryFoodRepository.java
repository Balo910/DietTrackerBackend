package com.diettracker.backend.repositories;

import com.diettracker.backend.models.DiaryFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryFoodRepository extends JpaRepository<DiaryFood, Long> {
}