package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelRespDTO {
    private Long levelId;

    private String description;
    private double maxPoints;
    private double minPoints;

    private List<QuestionDTO> question;
}
