package com.example.satoruquizzes.satoquiz.model.dto;

import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ReponseRespDto;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Raison cannot be blank")
    private String raison;

    @NotNull(message = "attemptNumber cannot be null")
    private Integer attemptNumber;

    @NotNull(message = "Final result cannot be null")
    private double finalResult;

    @NotNull(message = "Student cannot be null")
    private Long studentId;

    @NotNull(message = "Test cannot be null")
    private Long testId;


}