package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReponseDTO {

    private Long reponseId;

    @NotNull(message = "Question result cannot be null")
    private double questionResult;

    @NotNull(message = "Assign test ID cannot be null")
    private Long assignTestId;

}