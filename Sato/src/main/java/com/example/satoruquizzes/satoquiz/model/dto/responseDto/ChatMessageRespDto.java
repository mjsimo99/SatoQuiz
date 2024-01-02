package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import com.example.satoruquizzes.satoquiz.model.dto.ParticipateDTO;
import com.example.satoruquizzes.satoquiz.model.dto.SalonDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Salon;
import com.example.satoruquizzes.satoquiz.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRespDto {
    private Long id;
    private String content;
    private ParticipateDTO participate;
    private LocalDateTime timestamp;
}
