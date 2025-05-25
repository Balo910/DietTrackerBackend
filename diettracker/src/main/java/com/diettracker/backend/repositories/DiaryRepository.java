package com.diettracker.backend.repositories;
import com.diettracker.backend.models.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserEmail(String email);
}