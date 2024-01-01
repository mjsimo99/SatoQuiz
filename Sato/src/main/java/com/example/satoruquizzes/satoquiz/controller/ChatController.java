package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.ChatMessageDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ChatMessageRespDto;
import com.example.satoruquizzes.satoquiz.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;


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






    @Autowired
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
    public List<ChatMessageRespDto> getAllChatMessages() {
        List<ChatMessageRespDto> chatMessageRespDtoDTOs = chatService.getAllChatMessages();

        return chatMessageRespDtoDTOs.stream()
                .map(chatService::convertToDTO)
                .collect(Collectors.toList());
    }
    @RequestMapping(value = "/messages/{salonId}", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatMessageRespDto> getChatMessages(@PathVariable Long salonId) {
        List<ChatMessageRespDto> chatMessageRespDtoDTOs = chatService.getChatMessagesBySalonId(salonId);

        return chatMessageRespDtoDTOs.stream()
                .map(chatService::convertToDTO)
                .collect(Collectors.toList());
    }



}