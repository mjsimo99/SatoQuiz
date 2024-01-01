package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
@Table(name = "Users")
public abstract class User {
    @NotNull(message = "First name cannot be null")
    @Size(max = 255, message = "First name cannot exceed 255 characters")
    @Column(name = "firstName")
    protected String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(max = 255, message = "Last name cannot exceed 255 characters")
    @Column(name = "lastName")
    protected String lastName;

    @NotNull(message = "Date of birth cannot be null")
    @Column(name = "dateOfBirth")
    protected LocalDate dateOfBirth;

    @NotNull(message = "Address cannot be null")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    @Column(name = "address")
    protected String address;
}
