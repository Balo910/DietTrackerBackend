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

    private LocalDate date;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryFood> foods;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryFluid> fluids;

    public Diary() {
    }

    public Diary(LocalDate date) {
        this.date = date;
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

    public List<DiaryFood> getFoods() {
        return foods;
    }

    public void setFoods(List<DiaryFood> foods) {
        this.foods = foods;
    }

    public List<DiaryFluid> getFluids() {
        return fluids;
    }

    public void setFluids(List<DiaryFluid> fluids) {
        this.fluids = fluids;
    }
}