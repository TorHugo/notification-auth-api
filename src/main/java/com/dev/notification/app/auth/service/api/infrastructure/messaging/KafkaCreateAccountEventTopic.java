package com.dev.notification.app.auth.service.api.infrastructure.messaging;

import com.dev.notification.app.auth.service.api.application.ConfirmedAccountUseCase;
import com.dev.notification.app.auth.service.api.application.SaveAccountUseCase;
import com.dev.notification.app.auth.service.api.domain.exception.template.ListenerException;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.ConfirmedAccountEvent;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.CreateAccountEvent;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.ConfirmedAccountTopic;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.CreateAccountTopic;
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
    private final ConfirmedAccountUseCase confirmedAccountUseCase;
    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(topics = "CreateAccountEventTopic", groupId = "1001", containerFactory = "factory")
    public void createAccountListener(final String message,
                                      final Acknowledgment acknowledgment) {
        try {
            final var event = gson.fromJson(message, CreateAccountTopic.class);
            final var account = saveAccountUseCase.execute(event);
            eventPublisher.publishEvent(new CreateAccountEvent(this, account.getEmail().value(), message));
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
            throw new ListenerException("Error processing message!");
        } finally {
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = "ConfirmedAccountEventTopic", groupId = "1001", containerFactory = "factory")
    public void confirmedAccountListener(final String message,
                                         final Acknowledgment acknowledgment) {
        try {
            final var event = gson.fromJson(message, ConfirmedAccountTopic.class);
            confirmedAccountUseCase.execute(event);
            eventPublisher.publishEvent(new ConfirmedAccountEvent(this, event.getEmail(), message));
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
            throw new ListenerException("Error processing message!");
        } finally {
            acknowledgment.acknowledge();
        }
    }
}