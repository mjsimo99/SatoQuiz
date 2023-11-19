package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reponses")
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reponseId;

    @Column(name = "questionResult")
    private double questionResult;

    @OneToMany(mappedBy = "reponse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Validation> validations;

    @ManyToOne
    @JoinColumn(name = "assign_test_id")
    private AssignTest assignTest;
}