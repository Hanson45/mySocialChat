package com.mychatsocial.messageApi.service.impl;

import com.mychatsocial.messageApi.kafka.Producer;
import com.mychatsocial.messageApi.payload.Message;
import com.mychatsocial.messageApi.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final Producer messageProducer;
    @Override
    public void sendMessage(Message message) {
        messageProducer.sendMessage(message);
    }

    @Override
    public Message getMessageById(Long messageId) {
        return null;
    }

}
