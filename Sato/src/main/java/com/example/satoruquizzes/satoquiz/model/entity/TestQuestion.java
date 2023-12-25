package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Duration;


@Data
@Entity
@Table(name = "TestQuestions")
@IdClass(TestQuestionId.class)
public class TestQuestion implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Id
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private Integer temporize;
}