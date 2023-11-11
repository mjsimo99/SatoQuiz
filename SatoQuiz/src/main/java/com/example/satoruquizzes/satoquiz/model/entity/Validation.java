package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Validations")
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long validationId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;


}