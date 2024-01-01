package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LevelDTO {

    private Long levelId;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Max points cannot be null")
    @PositiveOrZero(message = "Max points must be a positive or zero value")
    private double maxPoints;

    @NotNull(message = "Min points cannot be null")
    @PositiveOrZero(message = "Min points must be a positive or zero value")
    private double minPoints;
}
