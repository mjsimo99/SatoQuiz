package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.entity.Room;
import com.example.satoruquizzes.satoquiz.model.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/greeting")
@CrossOrigin
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Room greet(Message message) {
        return new Room("Hello, " + HtmlUtils.htmlEscape(message.getText()) + "!");

    }

}


