package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignTestDTO {

    private Long assignTestId;

    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;

    private String raison;

    @NotNull(message = "Score cannot be null")
    private double score;

    private Integer attemptNumber;

    @NotNull(message = "Final result cannot be null")
    private double finalResult;

    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @NotNull(message = "Test ID cannot be null")
    private Long testId;

}