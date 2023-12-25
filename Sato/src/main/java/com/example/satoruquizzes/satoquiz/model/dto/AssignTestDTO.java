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



    private Integer attemptNumber;

    @NotNull(message = "Final result cannot be null")
    private double finalResult;

    @NotNull(message = "Student cannot be null")
    private StudentDTO student;

    @NotNull(message = "Test cannot be null")
    private TestDTO test;
}