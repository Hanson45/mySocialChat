package com.mychatsocial.messageApi.controller;

import com.mychatsocial.messageApi.kafka.Producer;
import com.mychatsocial.messageApi.payload.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
        private final Producer producer;
        private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Message messageDto) {
        Message message = Message.builder()
                .content(messageDto.getContent())
                .senderId(messageDto.getSenderId())
                .receiverId(messageDto.getReceiverId())
                .build();

        producer.sendMessage(message);

        // Envía una notificación a través de WebSocket
        messagingTemplate.convertAndSendToUser(String.valueOf(messageDto.getReceiverId()), "/queue/messages", message);

        return ResponseEntity.ok("Message sent successfully");
    }
}
