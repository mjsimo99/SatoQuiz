package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AnswerDTO {

    private Long answerId;

    @NotBlank(message = "Answer text cannot be blank")
    @Size(max = 255, message = "Answer text cannot exceed 255 characters")
    private String answerText;

    @NotNull(message = "Validation IDs cannot be null")
    private List<Long> validationIds;


}