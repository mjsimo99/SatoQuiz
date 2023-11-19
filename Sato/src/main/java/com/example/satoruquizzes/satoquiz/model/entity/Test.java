package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;
    @Column(name = "successScore")
    private String successScore;

    @Column(name = "viewAnswer")
    private boolean viewAnswer;

    @Column(name = "viewResult")
    private Boolean viewResult;

    @Column(name = "maxAttempt")
    private Integer maxAttempt;

    @Column(name = "remark")
    private String remark;

    @Column(name = "instructions")
    private String instructions;

    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private List<TestQuestion> testQuestions;

    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private List<AssignTest> assignTests;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}