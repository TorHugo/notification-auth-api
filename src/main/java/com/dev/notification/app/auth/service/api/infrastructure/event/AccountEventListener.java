package com.dev.notification.app.auth.service.api.infrastructure.event;

import com.dev.notification.app.auth.service.api.domain.Event;
import com.dev.notification.app.auth.service.api.domain.enums.EventType;
import com.dev.notification.app.auth.service.api.domain.exception.template.EventException;
import com.dev.notification.app.auth.service.api.domain.gateway.EventGateway;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.CreateAccountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountEventListener {
    private final EventGateway eventGateway;

    @EventListener
    public void handlerCreateAccount(final CreateAccountEvent createAccountEvent){
        log.info("Event: {}", createAccountEvent);
        try {
            final var event = Event.create(
                    createAccountEvent.getAggregateIdentifier(),
                    EventType.CREATE_ACCOUNT_EVENT,
                    createAccountEvent.getTransaction()
            );
            eventGateway.save(event);
        } catch (final Exception e) {
            log.error("Exception creating event: {}", e.getMessage());
            throw new EventException("Error creating event!");
        }
    }
}
