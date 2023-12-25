package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SubjectDTOResponse {

    private Long id;

    @NotBlank(message = "Intitule cannot be blank")
    private String intitule;
    private SubjectDTO parent;

    private List<SubjectDTO> children;

}