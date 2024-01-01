package com.example.satoruquizzes.satoquiz.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelId;

    @NotBlank(message = "Description cannot be blank")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Max points cannot be null")
    @PositiveOrZero(message = "Max points must be a positive or zero value")
    @Column(name = "maxPoints")
    private Double maxPoints;

    @NotNull(message = "Min points cannot be null")
    @PositiveOrZero(message = "Min points must be a positive or zero value")
    @Column(name = "minPoints")
    private Double minPoints;

    @OneToMany(mappedBy = "level")
    @JsonIgnore
    private List<Question> questions;
}
