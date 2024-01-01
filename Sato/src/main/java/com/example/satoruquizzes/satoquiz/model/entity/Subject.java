package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @NotBlank(message = "Intitule cannot be blank")
    private String intitule;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Subject parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Subject> children = new ArrayList<>();

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();
}
