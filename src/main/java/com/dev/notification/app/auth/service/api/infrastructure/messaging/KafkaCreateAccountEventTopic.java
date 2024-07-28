package com.dev.notification.app.auth.service.api.infrastructure.messaging;

import com.dev.notification.app.auth.service.api.application.SaveAccountUseCase;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.CreateAccountEvent;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.CreateAccountDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaCreateAccountEventTopic {

    private final Gson gson = new Gson();
    private final SaveAccountUseCase saveAccountUseCase;
    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(topics = "CreateAccountEventTopic", groupId = "1001", containerFactory = "factory")
    public void listener(final String message,
                            final Acknowledgment acknowledgment) {
        log.info("Receiving Message. This topic: CreateAccountEventTopic.");
        try {
            final var event = gson.fromJson(message, CreateAccountDTO.class);
            final var identifier = saveAccountUseCase.execute(event);
            eventPublisher.publishEvent(new CreateAccountEvent(this, identifier, message));
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
        } finally {
            acknowledgment.acknowledge();
        }
    }
}