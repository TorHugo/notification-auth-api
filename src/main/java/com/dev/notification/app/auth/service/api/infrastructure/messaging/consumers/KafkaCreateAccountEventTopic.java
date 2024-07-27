package com.dev.notification.app.auth.service.api.infrastructure.messaging.consumers;

import com.dev.notification.app.auth.service.api.application.SaveAccountUseCase;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.CreateAccountEvent;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaCreateAccountEventTopic {

    private final Gson gson = new Gson();
    private final SaveAccountUseCase saveAccountUseCase;

    @KafkaListener(topics = "CreateAccountEventTopic", groupId = "1001", containerFactory = "factory")
    public void execute(final String message, final Acknowledgment acknowledgment) {
        log.info("Consumer. This event: {}", message);
        try {
            final var event = gson.fromJson(message, CreateAccountEvent.class);
            saveAccountUseCase.execute(event);
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
        } finally {
            acknowledgment.acknowledge();
        }
    }
}