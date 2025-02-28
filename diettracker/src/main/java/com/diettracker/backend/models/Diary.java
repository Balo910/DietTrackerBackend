package com.diettracker.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DiaryType type;

    public Diary() {
    }

    public Diary(String name, DiaryType type) {
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiaryType getType() {
        return type;
    }

    public void setType(DiaryType type) {
        this.type = type;
    }
}