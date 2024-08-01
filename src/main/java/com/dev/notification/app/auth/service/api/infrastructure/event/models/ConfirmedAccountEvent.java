package com.dev.notification.app.auth.service.api.infrastructure.event.models;

import com.dev.notification.app.auth.service.api.infrastructure.event.BaseEvent;
import lombok.Getter;

@Getter
public class ConfirmedAccountEvent extends BaseEvent {
    public ConfirmedAccountEvent(final Object source,
                                 final String aggregateIdentifier,
                                 final String transaction) {
        super(source, aggregateIdentifier, transaction);
    }
}
