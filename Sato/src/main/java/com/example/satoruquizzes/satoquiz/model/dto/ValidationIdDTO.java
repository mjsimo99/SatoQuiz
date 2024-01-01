package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class ValidationIdDTO implements Serializable {
    @NotNull(message = "Question ID cannot be null")
    private Long questionId;

    @NotNull(message = "Answer ID cannot be null")
    private Long answerId;
}
