package com.example.satoruquizzes.satoquiz.model.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationDTO {
    private AnswerDTO answer;
    private QuestionDTO question;
    private double points;
}