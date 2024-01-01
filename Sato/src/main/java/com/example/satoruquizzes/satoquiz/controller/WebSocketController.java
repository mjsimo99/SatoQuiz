package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.ChatMessageDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ChatMessageRespDto;
import com.example.satoruquizzes.satoquiz.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final ChatService chatService;
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public WebSocketController(ChatService chatService, SimpMessageSendingOperations messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }


}
