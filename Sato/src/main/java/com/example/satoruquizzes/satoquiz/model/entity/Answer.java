package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @NotBlank(message = "Answer text cannot be blank")
    @Size(max = 255, message = "Answer text cannot exceed 255 characters")
    private String answerText;

    @NotNull(message = "Validation  cannot be null")
    @JsonIgnore
    @OneToMany(mappedBy = "answer")
    private List<Validation> validations;



}
