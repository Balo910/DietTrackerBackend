package com.diettracker.backend.diary;
import com.diettracker.backend.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByDiaryOwner(String diaryOwner);
}