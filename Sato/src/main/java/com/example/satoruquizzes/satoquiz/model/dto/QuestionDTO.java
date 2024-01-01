package com.example.satoruquizzes.satoquiz.model.dto;

import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ValidationRespDTO;
import com.example.satoruquizzes.satoquiz.model.enums.TypeAnswer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class QuestionDTO {

    private Long questionId;
    @Positive(message = "Answers number must be a positive value")
    private int answersNumber;
    @Positive(message = "Correct answers number must be a positive value")
    private int answersNumberCorrect;
    @NotBlank(message = "Question text cannot be blank")
    private String text;
    @NotNull(message = "Duration cannot be null")
    private LocalTime duration;
    @NotNull(message = "Type cannot be null")
    private TypeAnswer type;
    @Positive(message = "Score points must be a positive value")
    private double scorePoints;
    private Long subjectId;
    private Long levelId;

    private List<MediaDTO> mediaList;

    //private List<ValidationRespDTO> validations;

}