package com.example.satoruquizzes.satoquiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateDTO {

    @Valid
    private ParticipateIdDTO participateId;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;
}
