package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.ChatMessageDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ChatMessageRespDTO;
import com.example.satoruquizzes.satoquiz.service.ChatService;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:4200/")
public class ChatController {

    private final ChatService chatService;
    public ChatController(ChatService chatService){
        this.chatService = chatService;


    }
    @MessageMapping("/chat.sendMessage/{salonId}")
    @SendTo("/topic/salon/{salonId}")
    public ChatMessageDTO handleChatMessage(@DestinationVariable Long salonId, ChatMessageDTO messageDTO) {
        chatService.saveChatMessage(messageDTO.getStudentId(), salonId, messageDTO.getContent());
        return messageDTO;
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatMessageRespDTO> getAllChatMessages() {
        List<ChatMessageRespDTO> chatMessageRespDtoDTOS = chatService.getAllChatMessages();

        return chatMessageRespDtoDTOS.stream()
                .map(chatService::convertToDTO)
                .collect(Collectors.toList());
    }
    @RequestMapping(value = "/messages/{salonId}", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatMessageRespDTO> getChatMessages(@PathVariable Long salonId) {
        List<ChatMessageRespDTO> chatMessageRespDtoDTOS = chatService.getChatMessagesBySalonId(salonId);

        return chatMessageRespDtoDTOS.stream()
                .map(chatService::convertToDTO)
                .collect(Collectors.toList());
    }



}