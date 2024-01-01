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

    // Constructor injecting ChatService dependency
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    // WebSocket endpoint for sending and receiving chat messages
    @MessageMapping("/chat.sendMessage/{salonId}")
    @SendTo("/topic/salon/{salonId}")
    public ChatMessageDTO handleChatMessage(@DestinationVariable Long salonId, ChatMessageDTO messageDTO) {
        // Save the chat message using the service
        chatService.saveChatMessage(messageDTO.getStudentId(), salonId, messageDTO.getContent());
        // Broadcast the received message to the specified salon topic
        return messageDTO;
    }

    // HTTP endpoint to retrieve all chat messages
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatMessageRespDTO> getAllChatMessages() {
        // Retrieve all chat messages using the service
        List<ChatMessageRespDTO> chatMessageRespDtoDTOS = chatService.getAllChatMessages();

        // Convert and return the list of chat message response DTOs
        return chatMessageRespDtoDTOS.stream()
                .map(chatService::convertToDTO)
                .collect(Collectors.toList());
    }

    // HTTP endpoint to retrieve chat messages by salon ID
    @RequestMapping(value = "/messages/{salonId}", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatMessageRespDTO> getChatMessages(@PathVariable Long salonId) {
        // Retrieve chat messages by salon ID using the service
        List<ChatMessageRespDTO> chatMessageRespDtoDTOS = chatService.getChatMessagesBySalonId(salonId);

        // Convert and return the list of chat message response DTOs
        return chatMessageRespDtoDTOS.stream()
                .map(chatService::convertToDTO)
                .collect(Collectors.toList());
    }
}
