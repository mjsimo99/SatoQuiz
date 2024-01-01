package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnswerDTO {

    private Long answerId;

    @NotBlank(message = "Answer text cannot be blank")
    @Size(max = 255, message = "Answer text cannot exceed 255 characters")
    private String answerText;

}
