package com.dev.notification.app.auth.service.api.infrastructure.messaging;

import com.dev.notification.app.auth.service.api.application.ConfirmedAccount;
import com.dev.notification.app.auth.service.api.application.ResetPassword;
import com.dev.notification.app.auth.service.api.application.SaveAccount;
import com.dev.notification.app.auth.service.api.domain.exception.template.ListenerException;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.ConfirmedAccountEvent;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.CreateAccountEvent;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.ResetPasswordEvent;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.ConfirmedAccountTopic;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.CreateAccountTopic;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.ResetPasswordTopic;
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
public class KafkaAccountConsumerListener {

    private final ApplicationEventPublisher eventPublisher;
    private final Gson gson = new Gson();
    private final SaveAccount saveAccount;
    private final ConfirmedAccount confirmedAccount;
    private final ResetPassword resetPassword;

    @KafkaListener(topics = "CreateAccountEventTopic", groupId = "1001", containerFactory = "factory")
    public void createAccountListener(final String message,
                                      final Acknowledgment acknowledgment) {
        try {
            final var event = gson.fromJson(message, CreateAccountTopic.class);
            final var account = saveAccount.execute(event);
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
            confirmedAccount.execute(event);
            eventPublisher.publishEvent(new ConfirmedAccountEvent(this, event.getEmail(), message));
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
            throw new ListenerException("Error processing message!");
        } finally {
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = "ResetPasswordEventTopic", groupId = "1001", containerFactory = "factory")
    public void resetPasswordListener(final String message,
                                      final Acknowledgment acknowledgment) {
        try {
            final var event = gson.fromJson(message, ResetPasswordTopic.class);
            resetPassword.execute(event);
            eventPublisher.publishEvent(new ResetPasswordEvent(this, event.getEmail(), message));
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
            throw new ListenerException("Error processing message!");
        } finally {
            acknowledgment.acknowledge();
        }
    }
}