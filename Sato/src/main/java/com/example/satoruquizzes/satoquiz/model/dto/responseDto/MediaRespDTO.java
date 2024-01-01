package com.example.satoruquizzes.satoquiz.model.dto.responseDto;


import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import com.example.satoruquizzes.satoquiz.model.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaRespDTO {

    private Long mediaId;

    @NotBlank(message = "Link cannot be blank")
    private String link;

    @NotNull(message = "Type cannot be null")
    private MediaType type;

    private QuestionDTO question;



}
