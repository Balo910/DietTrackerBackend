package com.diettracker.backend.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "diary")
public class Diary {
    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiaryFood> diaryFoods;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaryType name;

    private LocalDate date;

    public Diary() {
    }

    public Diary(DiaryType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiaryType getName() {
        return name;
    }

    public void setName(DiaryType name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<DiaryFood> getDiaryFoods() {
        return diaryFoods;
    }

    public void setDiaryFoods(List<DiaryFood> diaryFoods) {
        this.diaryFoods = diaryFoods;
    }

    public LocalDate getDate() {
        return date;
    }
}