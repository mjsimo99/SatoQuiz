package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TestQuestionId implements Serializable {

    @Column(name = "test_id")
    private Long testId;

    @Column(name = "question_id")
    private Long questionId;
}