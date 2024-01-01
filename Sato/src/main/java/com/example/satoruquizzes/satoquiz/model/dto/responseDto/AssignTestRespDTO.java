package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import com.example.satoruquizzes.satoquiz.model.dto.ReponseDTO;
import com.example.satoruquizzes.satoquiz.model.dto.StudentDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignTestRespDTO {
    private Long assignTestId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String raison;
    private Integer attemptNumber;
    private double finalResult;
    private StudentDTO student;
    private TestDTO test;

    private List<ReponseDTO> reponses;

}
