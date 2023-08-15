package com.mychatsocial.messageApi.controller;

import com.mychatsocial.messageApi.payload.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message msg){
        return msg;
    }

    public Message addUser(@Payload Message msg, SimpMessageHeaderAccessor headerAccessor){
        //añade usernae y sesion de socket
        headerAccessor.getSessionAttributes().put("username", msg.getSenderId());
        return msg;
    }
}
