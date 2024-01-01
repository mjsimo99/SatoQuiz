package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import com.example.satoruquizzes.satoquiz.model.dto.AssignTestDTO;
import com.example.satoruquizzes.satoquiz.model.dto.ValidationDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ReponseRespDto {

    private Long reponseId;

    @NotNull(message = "Question result cannot be null")
    private double questionResult;

    @NotNull(message = "Assign test cannot be null")
    private AssignTestDTO assignTest;

    private ValidationDTO validation;




}
