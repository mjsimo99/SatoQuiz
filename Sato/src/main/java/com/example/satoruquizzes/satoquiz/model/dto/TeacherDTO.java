package com.example.satoruquizzes.satoquiz.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDTO extends UserDTO {

    @NotNull(message = "Teacher ID cannot be null")
    private Long teacherId;

    @NotBlank(message = "Specialty cannot be blank")
    @Size(max = 255, message = "Specialty cannot exceed 255 characters")
    private String specialty;
}