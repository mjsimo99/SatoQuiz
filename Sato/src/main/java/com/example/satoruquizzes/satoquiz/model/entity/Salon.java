package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "salon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Salon name cannot be blank")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "salon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participate> participates;
}
