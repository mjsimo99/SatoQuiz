package com.example.satoruquizzes.satoquiz.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidationIdDTO implements Serializable {
    private Long questionId;
    private Long answerId;
}