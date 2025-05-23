package com.diettracker.backend.repositories;

import com.diettracker.backend.models.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserEmail(String userEmail);
    Optional<Diary> findByIdAndUserEmail(Long id, String userEmail);
}