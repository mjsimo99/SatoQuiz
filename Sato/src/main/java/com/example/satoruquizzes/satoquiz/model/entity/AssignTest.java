package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;

    @NotBlank(message = "Reason cannot be blank")
    private String reason;

    @NotNull(message = "Attempt number cannot be null")
    @Positive(message = "Attempt number must be a positive value")
    private Integer attemptNumber;

    @NotNull(message = "Final result cannot be null")
    @Positive(message = "Final result must be a positive value")
    private Double finalResult;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @NotNull(message = "Student cannot be null")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @NotNull(message = "Test cannot be null")
    private Test test;

    @OneToMany(mappedBy = "assignTest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reponse> reponses;
}
