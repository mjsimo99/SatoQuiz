package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import jakarta.validation.constraints.*;

@Data
public class TestDTO {

    private Long testId;

    @NotBlank(message = "Success score cannot be blank")
    private String successScore;

    @NotNull(message = "View answer cannot be null")
    private Boolean viewAnswer;

    @NotNull(message = "View result cannot be null")
    private Boolean viewResult;

    @NotNull(message = "Max attempt cannot be null")
    @Min(value = 1, message = "Max attempt must be at least 1")
    private Integer maxAttempt;

    @Size(max = 255, message = "Remark cannot exceed 255 characters")
    private String remark;

    @Size(max = 255, message = "Instructions cannot exceed 255 characters")
    private String instructions;

    @NotNull(message = "Teacher cannot be null")
    private Long teacherId;



}