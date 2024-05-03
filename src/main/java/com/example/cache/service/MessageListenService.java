package com.example.cache.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MessageListenService implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("Received {} channel: {}", new String(message.getChannel()), new String(message.getBody()))    ;
    }
}
