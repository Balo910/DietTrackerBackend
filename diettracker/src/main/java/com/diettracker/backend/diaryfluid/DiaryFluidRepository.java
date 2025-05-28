package com.diettracker.backend.diaryfluid;

import com.diettracker.backend.diaryfluid.DiaryFluid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DiaryFluidRepository extends JpaRepository<DiaryFluid, Long> {
    Optional<DiaryFluid> findByDiaryIdAndFluidId(Long diaryId, Long fluidId);
}