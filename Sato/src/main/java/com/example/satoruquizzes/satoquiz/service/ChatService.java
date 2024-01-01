package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.CustomRuntimeException;
import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ChatMessageRespDTO;
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

    public ChatService(ChatMessageRepository chatMessageRepository, ModelMapper modelMapper, ParticipateRepository participateRepository) {
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
            throw new CustomRuntimeException("Error while saving chat message: " + e.getMessage());
        }
    }

    public List<ChatMessageRespDTO> getChatMessagesBySalonId(Long salonId) {
        try {
            List<ChatMessage> chatMessages = chatMessageRepository.findByParticipate_Salon_IdOrderByTimestampAsc(salonId);
            return chatMessages.stream()
                    .map(chatMessage -> modelMapper.map(chatMessage, ChatMessageRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching chat messages by salonId: " + salonId + ": " + e.getMessage());
        }
    }

    public List<ChatMessageRespDTO> getAllChatMessages() {
        try {
            List<ChatMessage> chatMessages = chatMessageRepository.findAll();
            return chatMessages.stream()
                    .map(chatMessage -> modelMapper.map(chatMessage, ChatMessageRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching all chat messages: " + e.getMessage());
        }
    }

    public ChatMessageRespDTO convertToDTO(ChatMessageRespDTO chatMessageRespDto) {
        return modelMapper.map(chatMessageRespDto, ChatMessageRespDTO.class);
    }
}
