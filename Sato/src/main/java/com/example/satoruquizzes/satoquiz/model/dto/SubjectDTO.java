package com.example.satoruquizzes.satoquiz.model.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class SubjectDTO {

    private Long id;

    @NotBlank(message = "Intitule cannot be blank")
    private String intitule;
    private SubjectDTO parent;


}