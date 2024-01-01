package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import com.example.satoruquizzes.satoquiz.model.dto.AssignTestDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TeacherDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TestQuestionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResDTO {
    private Long testId;
    private String successScore;
    private boolean viewAnswer;
    private Boolean viewResult;
    private Integer maxAttempt;
    private String remark;
    private String instructions;
    private List<TestQuestionDTO> testQuestions;
    private List<AssignTestDTO> assignTests;
    private TeacherDTO teacher;
}
