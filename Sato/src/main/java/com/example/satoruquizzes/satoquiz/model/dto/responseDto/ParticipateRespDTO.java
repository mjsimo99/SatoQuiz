package com.example.satoruquizzes.satoquiz.model.dto.responseDto;

import com.example.satoruquizzes.satoquiz.model.dto.ChatMessageDTO;
import com.example.satoruquizzes.satoquiz.model.dto.ParticipateIdDTO;
import com.example.satoruquizzes.satoquiz.model.dto.SalonDTO;
import com.example.satoruquizzes.satoquiz.model.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipateRespDTO {
    private ParticipateIdDTO participateId;
    private LocalDate date;
    private StudentDTO student;
    private SalonDTO salon;
    private List<ChatMessageDTO> chatMessages;
}
