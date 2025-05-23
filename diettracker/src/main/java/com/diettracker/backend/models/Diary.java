package com.diettracker.backend.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaryType name;

    private LocalDate date;

    @Column(name = "user_email")
    private String userEmail;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiaryFood> diaryFoods;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiaryFluid> diaryFluids;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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