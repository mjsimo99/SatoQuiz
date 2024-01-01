package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelId;

    @Column(name = "description")
    private String description;

    @Column(name = "maxPoints")
    private double maxPoints;

    @Column(name = "minPoints")
    private double minPoints;

    @OneToMany(mappedBy = "level")
    @JsonIgnore
    private List<Question> questions;
}