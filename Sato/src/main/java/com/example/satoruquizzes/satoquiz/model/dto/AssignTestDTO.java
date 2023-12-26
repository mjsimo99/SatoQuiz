package com.example.satoruquizzes.satoquiz.model.dto;

import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ReponseRespDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssignTestDTO {

    private Long assignTestId;

    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;

    private String raison;



    private Integer attemptNumber;

    @NotNull(message = "Final result cannot be null")
    private double finalResult;

    @NotNull(message = "Student cannot be null")
    private StudentDTO student;

    @NotNull(message = "Test cannot be null")
    private TestDTO test;

    private List<ReponseRespDto> reponses;

}