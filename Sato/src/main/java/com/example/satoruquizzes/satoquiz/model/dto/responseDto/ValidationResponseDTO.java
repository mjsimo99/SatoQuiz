package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import com.example.satoruquizzes.satoquiz.model.dto.AnswerDTO;
import lombok.Data;
@Data
public class ValidationResponseDTO {
        private QuestionRespDTO question;
        private AnswerDTO answer;
        private double points;

}
