package com.example.satoruquizzes.satoquiz.model.entity;

import com.example.satoruquizzes.satoquiz.model.enums.TypeAnswer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "questionId")
    private Long questionId;

    @Column(name = "answersNumber")
    private int answersNumber;

    @Column(name = "answersNumberCorrect")
    private int answersNumberCorrect;

    @Column(name = "text")
    private String text;


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeAnswer type;

    @Column(name = "scorePoints")
    private double scorePoints;
    @JsonIgnore


    @ManyToOne
    @JoinColumn(name = "subject")
    private Subject subject;
    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "level")
    private Level level;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Validation> validations;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestQuestion> testQuestions;


}