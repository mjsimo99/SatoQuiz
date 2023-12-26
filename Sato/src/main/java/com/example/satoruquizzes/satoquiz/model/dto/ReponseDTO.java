package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseDTO {

    private Long reponseId;

    @NotNull(message = "Question result cannot be null")
    private double questionResult;

    @NotNull(message = "Assign test cannot be null")
    private AssignTestDTO assignTest;

    private ValidationDTO validation;

    //private AnswerDTO answer;

    //private QuestionDTO question;

}