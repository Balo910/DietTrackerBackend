package com.diettracker.backend.repositories;

import com.diettracker.backend.models.DiaryFluid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DiaryFluidRepository extends JpaRepository<DiaryFluid, Long> {
    Optional<DiaryFluid> findByDiaryIdAndFluidId(Long diaryId, Long fluidId);
}