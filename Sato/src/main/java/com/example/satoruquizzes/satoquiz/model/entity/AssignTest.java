package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AssignTests")
public class AssignTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignTestId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String raison;
    private Integer attemptNumber;
    private double finalResult;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "assignTest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reponse> reponses;
}