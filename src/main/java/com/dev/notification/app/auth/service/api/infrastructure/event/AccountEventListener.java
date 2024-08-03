package com.dev.notification.app.auth.service.api.infrastructure.event;

import com.dev.notification.app.auth.service.api.domain.entity.Event;
import com.dev.notification.app.auth.service.api.domain.enums.EventType;
import com.dev.notification.app.auth.service.api.domain.exception.template.EventException;
import com.dev.notification.app.auth.service.api.domain.gateway.EventGateway;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.ConfirmedAccountEvent;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.CreateAccountEvent;
import com.dev.notification.app.auth.service.api.infrastructure.event.models.ResetPasswordEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEventListener {
    private final EventGateway eventGateway;

    @EventListener
    public void handlerCreateAccount(final CreateAccountEvent entryEvent){
        try {
            final var event = Event.create(
                    entryEvent.getAggregateIdentifier(),
                    EventType.CREATE_ACCOUNT_EVENT,
                    entryEvent.getTransaction()
            );
            eventGateway.save(event);
        } catch (final Exception e) {
            throw new EventException("Error creating event!");
        }
    }

    @EventListener
    public void handlerConfirmedAccount(final ConfirmedAccountEvent entryEvent){
        try {
            final var event = Event.create(
                    entryEvent.getAggregateIdentifier(),
                    EventType.CONFIRMED_ACCOUNT_EVENT,
                    entryEvent.getTransaction()
            );
            eventGateway.save(event);
        } catch (final Exception e) {
            throw new EventException("Error creating event!");
        }
    }

    @EventListener
    public void handlerResetPassword(final ResetPasswordEvent entryEvent){
        try {
            final var event = Event.create(
                    entryEvent.getAggregateIdentifier(),
                    EventType.RESET_PASSWORD_EVENT,
                    entryEvent.getTransaction()
            );
            eventGateway.save(event);
        } catch (final Exception e) {
            throw new EventException("Error creating event!");
        }
    }
}
