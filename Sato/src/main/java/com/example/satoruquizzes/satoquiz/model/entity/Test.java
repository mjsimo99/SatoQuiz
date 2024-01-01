package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Success score cannot be blank")
    @Column(name = "successScore")
    private String successScore;

    @NotNull(message = "View answer cannot be null")
    @Column(name = "viewAnswer")
    private Boolean viewAnswer;

    @NotNull(message = "View result cannot be null")
    @Column(name = "viewResult")
    private Boolean viewResult;

    @NotNull(message = "Max attempt cannot be null")
    @Min(value = 1, message = "Max attempt must be at least 1")
    @Column(name = "maxAttempt")
    private Integer maxAttempt;

    @Size(max = 255, message = "Remark cannot exceed 255 characters")
    @Column(name = "remark")
    private String remark;

    @Size(max = 255, message = "Instructions cannot exceed 255 characters")
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
    @NotNull(message = "Teacher cannot be null")
    private Teacher teacher;
}
