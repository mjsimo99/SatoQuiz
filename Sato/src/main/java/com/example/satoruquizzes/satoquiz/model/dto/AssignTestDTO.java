package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssignTestDTO {

    private Long assignTestId;

    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;

    @NotBlank(message = "Reason cannot be blank")
    private String reason;

    @NotNull(message = "Attempt number cannot be null")
    @Positive(message = "Attempt number must be a positive value")
    private Integer attemptNumber;

    @NotNull(message = "Final result cannot be null")
    @Positive(message = "Final result must be a positive value")
    private Double finalResult;

    @NotNull(message = "Student ID cannot be null")
    @Positive(message = "Student ID must be a positive value")
    private Long studentId;

    @NotNull(message = "Test ID cannot be null")
    @Positive(message = "Test ID must be a positive value")
    private Long testId;
}
