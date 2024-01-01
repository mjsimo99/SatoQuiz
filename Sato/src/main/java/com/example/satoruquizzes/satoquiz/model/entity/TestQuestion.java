package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "TestQuestions")
@IdClass(TestQuestionId.class)
public class TestQuestion implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "test_id")
    @NotNull(message = "Test cannot be null")
    private Test test;

    @Id
    @ManyToOne
    @JoinColumn(name = "question_id")
    @NotNull(message = "Question cannot be null")
    private Question question;

    private Integer temporize;
}
