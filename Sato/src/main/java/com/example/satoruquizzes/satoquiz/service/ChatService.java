package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ChatMessageRespDto;
import com.example.satoruquizzes.satoquiz.model.entity.ChatMessage;
import com.example.satoruquizzes.satoquiz.model.entity.Participate;
import com.example.satoruquizzes.satoquiz.repository.ChatMessageRepository;
import com.example.satoruquizzes.satoquiz.repository.ParticipateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ModelMapper modelMapper;

    private final ParticipateRepository participateRepository;



    public ChatService(ChatMessageRepository chatMessageRepository, ModelMapper modelMapper ,ParticipateRepository participateRepository){
        this.chatMessageRepository = chatMessageRepository;
        this.modelMapper = modelMapper;
        this.participateRepository = participateRepository;
    }

    public void saveChatMessage(Long studentId, Long salonId, String content) {
        try {
            Optional<Participate> participateOptional = participateRepository.findByStudentIdAndSalonId(studentId, salonId);
            if (participateOptional.isPresent()) {
                Participate participate = participateOptional.get();
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setContent(content);
                chatMessage.setParticipate(participate);
                chatMessage.setTimestamp(LocalDateTime.now());
                chatMessageRepository.save(chatMessage);
            } else {
                throw new NotFoundException("Participate not found for studentId: " + studentId + " and salonId: " + salonId);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public List<ChatMessageRespDto> getChatMessagesBySalonId(Long salonId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByParticipate_Salon_IdOrderByTimestampAsc(salonId);
        return chatMessages.stream()
                .map(chatMessage -> modelMapper.map(chatMessage, ChatMessageRespDto.class))
                .collect(Collectors.toList());
    }

    public List<ChatMessageRespDto> getAllChatMessages() {
        List<ChatMessage> chatMessages = chatMessageRepository.findAll();
        return chatMessages.stream()
                .map(chatMessage -> modelMapper.map(chatMessage, ChatMessageRespDto.class))
                .collect(Collectors.toList());
    }

    public ChatMessageRespDto convertToDTO(ChatMessageRespDto chatMessageRespDto) {
        return modelMapper.map(chatMessageRespDto, ChatMessageRespDto.class);
    }
}
