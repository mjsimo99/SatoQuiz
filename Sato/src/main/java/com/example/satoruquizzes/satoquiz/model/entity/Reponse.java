package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Reponses")
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reponseId;

    @Column(name = "questionResult")
    private double questionResult;

    @ManyToOne
    @JoinColumn(name = "assign_test_id")
    private AssignTest assignTest;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "question_id", referencedColumnName = "question_id"),
            @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")/* ,

           */
    })
    @OrderColumn(name = "validation_order")
    private Validation validation;
}
