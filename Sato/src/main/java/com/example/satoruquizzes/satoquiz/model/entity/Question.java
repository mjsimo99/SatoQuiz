package com.example.satoruquizzes.satoquiz.model.entity;

import com.example.satoruquizzes.satoquiz.model.enums.TypeAnswer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Positive(message = "Answers number must be a positive value")
    @Column(name = "answersNumber")
    private int answersNumber;

    @Positive(message = "Correct answers number must be a positive value")
    @Column(name = "answersNumberCorrect")
    private int answersNumberCorrect;

    @NotBlank(message = "Question text cannot be blank")
    @Column(name = "text")
    private String text;

    @NotNull(message = "Type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeAnswer type;

    @Positive(message = "Score points must be a positive value")
    @Column(name = "scorePoints")
    private double scorePoints;

    @NotNull(message = "Subject cannot be null")
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @NotNull(message = "Level cannot be null")
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Validation> validations;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestQuestion> testQuestions;
}
