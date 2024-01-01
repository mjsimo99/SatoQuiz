package com.example.satoruquizzes.satoquiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateDTO {
    private ParticipateIdDTO participateId;
    private LocalDate date;

}
