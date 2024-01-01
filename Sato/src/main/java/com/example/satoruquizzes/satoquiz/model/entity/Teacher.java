package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Teachers")
@Inheritance
public class Teacher extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherId")
    private Long teacherId;

    @NotBlank(message = "Specialty cannot be blank")
    @Size(max = 255, message = "Specialty cannot exceed 255 characters")
    @Column(name = "specialty")
    private String specialty;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> tests;
}
