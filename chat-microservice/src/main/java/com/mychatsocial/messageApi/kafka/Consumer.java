package com.mychatsocial.messageApi.kafka;

import com.mychatsocial.messageApi.payload.Message;
import com.mychatsocial.messageApi.payload.user.User;
import com.mychatsocial.messageApi.payload.user.UserService;
import com.mychatsocial.messageApi.service.MessageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Consumer {
    private final UserService userService;
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics="chat", groupId = "myGroup")
    public void consume(Message message){
        System.out.println("desde el consumer: " + message);

        //send with socket
        messagingTemplate.convertAndSend("/topic/chat", message);

        LOGGER.info(String.format("Json recieved -> %s", message.getContent()));
    }
}
