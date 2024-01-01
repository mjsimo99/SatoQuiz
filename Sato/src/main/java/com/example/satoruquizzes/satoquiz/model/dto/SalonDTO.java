package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalonDTO {

    private Long salonId;

    @NotBlank(message = "Salon name cannot be blank")
    private String name;
}
