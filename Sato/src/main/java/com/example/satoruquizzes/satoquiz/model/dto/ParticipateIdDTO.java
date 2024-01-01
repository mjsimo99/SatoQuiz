package com.example.satoruquizzes.satoquiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateIdDTO {
    private StudentDTO student;
    private SalonDTO salon;
}
