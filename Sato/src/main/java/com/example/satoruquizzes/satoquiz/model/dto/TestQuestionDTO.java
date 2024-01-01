package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestionDTO {

    @NotNull(message = "Test ID cannot be null")
    private Long testId;

    @NotNull(message = "Question ID cannot be null")
    private Long questionId;

    private Integer temporize;
}
