package com.mychatsocial.messageApi.kafka;

import com.mychatsocial.messageApi.payload.Message;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message) {
        LOGGER.info(String.format("Message enviado -> %s", message.toString()));
        kafkaTemplate.send("chat", message);
    }
}
