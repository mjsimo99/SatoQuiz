package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    @NotNull(message = "First name cannot be null")
    @Size(max = 255, message = "First name cannot exceed 255 characters")
    protected String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(max = 255, message = "Last name cannot exceed 255 characters")
    protected String lastName;

    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

    @NotNull(message = "Address cannot be null")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    protected String address;


    @NotNull(message = "Username cannot be null")
    @Size(max = 255, message = "Username cannot exceed 255 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "User type cannot be null")
    private String userType;


}
