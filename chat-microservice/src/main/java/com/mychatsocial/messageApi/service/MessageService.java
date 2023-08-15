package com.mychatsocial.messageApi.service;

import com.mychatsocial.messageApi.payload.Message;

public interface MessageService {
    void sendMessage(Message message);

    Message getMessageById(Long messageId);
}
