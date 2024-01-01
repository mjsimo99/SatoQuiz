package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TestDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.model.entity.Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestionRespDTO {
    private TestDTO test;
    private QuestionDTO question;
    private Integer temporize;
}
