package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TestQuestions")
public class TestQuestion {

    private LocalTime time;
    private double score;
    private Test test;
    private Question question;
}
