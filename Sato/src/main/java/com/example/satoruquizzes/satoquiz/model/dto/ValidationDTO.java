package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationDTO {
    @NotNull(message = "Answer ID cannot be null")
    private Long answerId;

    @NotNull(message = "Question ID cannot be null")
    private Long questionId;

    @NotNull(message = "Points cannot be null")
    private double points;
}
