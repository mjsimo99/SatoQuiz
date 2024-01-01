package com.example.satoruquizzes.satoquiz.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

    private Long messageId;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @NotNull(message = "Salon ID cannot be null")
    private Long salonId;

    @CreationTimestamp
    private LocalDateTime timestamp;
}
