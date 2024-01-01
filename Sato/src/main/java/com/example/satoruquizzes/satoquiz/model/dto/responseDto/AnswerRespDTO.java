package com.example.satoruquizzes.satoquiz.model.dto.responseDto;


import com.example.satoruquizzes.satoquiz.model.dto.ValidationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRespDTO {
    private Long answerId;
    private String answerText;
    private List<ValidationDTO> validation;
}
