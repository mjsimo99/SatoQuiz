package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDTO {

    @NotNull(message = "User type cannot be null")
    private String userType;


    @NotNull(message = "Username cannot be null")
    @Size(max = 255, message = "Username cannot exceed 255 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;


}