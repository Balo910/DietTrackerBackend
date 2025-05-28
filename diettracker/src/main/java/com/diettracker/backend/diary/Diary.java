package com.diettracker.backend.diary;

import com.diettracker.backend.diaryfluid.DiaryFluid;
import com.diettracker.backend.diaryfood.DiaryFood;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @Column(name = "diary_owner")
    private String diaryOwner;


    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiaryFood> diaryFoods;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiaryFluid> diaryFluids;

    public Diary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDiaryOwner() {
        return diaryOwner;
    }

    public void setDiaryOwner(String diaryOwner) {
        this.diaryOwner = diaryOwner;
    }

    public List<DiaryFood> getDiaryFoods() {
        return diaryFoods;
    }

    public void setDiaryFoods(List<DiaryFood> diaryFoods) {
        this.diaryFoods = diaryFoods;
    }

    public List<DiaryFluid> getDiaryFluids() {
        return diaryFluids;
    }

    public void setDiaryFluids(List<DiaryFluid> diaryFluids) {
        this.diaryFluids = diaryFluids;
    }
}