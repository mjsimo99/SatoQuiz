package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Validations")
@IdClass(ValidationId.class)
public class Validation {

    @Id
    @ManyToOne
    @JoinColumn(name = "question_id")
    @NotNull(message = "Question cannot be null")
    private Question question;

    @Id
    @ManyToOne
    @JoinColumn(name = "answer_id")
    @NotNull(message = "Answer cannot be null")
    private Answer answer;

    @NotNull(message = "Points cannot be null")
    private double points;

    @JsonIgnore
    @OneToMany(mappedBy = "validation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reponse> reponses = new ArrayList<>();
}
