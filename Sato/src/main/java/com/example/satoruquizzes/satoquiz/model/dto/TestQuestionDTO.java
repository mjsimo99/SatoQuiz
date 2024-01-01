package com.example.satoruquizzes.satoquiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestionDTO {

    private Long testId;
    private Long questionId;
    private Integer temporize;
}