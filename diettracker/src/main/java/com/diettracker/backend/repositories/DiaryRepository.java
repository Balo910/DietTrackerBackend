package com.diettracker.backend.repositories;

import com.diettracker.backend.models.Diary;
import com.diettracker.backend.models.DiaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByNameContainingIgnoreCase(String name);
    List<Diary> findByType(DiaryType type);
}