package com.example.satoruquizzes.satoquiz.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

    private Long id;
    private String content;
    private Long studentId;
    private Long salonId;

    private LocalDateTime timestamp;

}
