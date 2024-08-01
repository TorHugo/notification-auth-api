package com.dev.notification.app.auth.service.api.infrastructure.event;

import com.dev.notification.app.auth.service.api.domain.entity.Event;
import com.dev.notification.app.auth.service.api.domain.enums.EventType;
import com.dev.notification.app.auth.service.api.domain.exception.template.EventException;
import com.dev.notification.app.auth.service.api.domain.gateway.EventGateway;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.ConfirmedAccountEvent;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.CreateAccountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEventListener {
    private final EventGateway eventGateway;

    @EventListener
    public void handlerCreateAccount(final CreateAccountEvent createAccountEvent){
        try {
            final var event = Event.create(
                    createAccountEvent.getAggregateIdentifier(),
                    EventType.CREATE_ACCOUNT_EVENT,
                    createAccountEvent.getTransaction()
            );
            eventGateway.save(event);
        } catch (final Exception e) {
            throw new EventException("Error creating event!");
        }
    }

    @EventListener
    public void handlerConfirmedAccount(final ConfirmedAccountEvent createAccountEvent){
        try {
            final var event = Event.create(
                    createAccountEvent.getAggregateIdentifier(),
                    EventType.CONFIRMED_ACCOUNT_EVENT,
                    createAccountEvent.getTransaction()
            );
            eventGateway.save(event);
        } catch (final Exception e) {
            throw new EventException("Error creating event!");
        }
    }
}
