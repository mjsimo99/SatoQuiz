package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import lombok.Data;


@Data
public class ReponseRespDto {
    private Long reponseId;
    private double questionResult;
    private ValidationRespDTO validation;


}
