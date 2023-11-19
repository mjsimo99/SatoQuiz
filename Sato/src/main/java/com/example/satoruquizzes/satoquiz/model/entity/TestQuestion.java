package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.time.Duration;

@Data
@Entity
@Table(name = "TestQuestions")
public class TestQuestion {

    @EmbeddedId
    private TestQuestionId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("testId")
    private Test test;

    @JsonIgnore
    @ManyToOne
    @MapsId("questionId")
    private Question question;

    private Duration temporize;
}
