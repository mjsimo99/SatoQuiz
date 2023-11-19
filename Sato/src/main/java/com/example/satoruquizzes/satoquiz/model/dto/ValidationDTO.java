package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationDTO {
    private Long answerId;
    private Long questionId;
    @Positive(message = "Points must be a positive value")
    private double points;

}