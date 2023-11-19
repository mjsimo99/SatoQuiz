package com.example.satoruquizzes.satoquiz.model.dto;

import com.example.satoruquizzes.satoquiz.model.enums.MediaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class MediaDTO {
    private Long mediaId;

    @NotBlank(message = "Link cannot be blank")
    private String link;

    @NotNull(message = "Type cannot be null")
    private MediaType type;


    // @JsonProperty("questionDTO")
    // private QuestionDTO questionDTO;
}