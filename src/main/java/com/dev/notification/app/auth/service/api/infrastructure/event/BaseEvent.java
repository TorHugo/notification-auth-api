package com.dev.notification.app.auth.service.api.infrastructure.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class BaseEvent extends ApplicationEvent {

    private final String aggregateIdentifier;
    private final String transaction;

    protected BaseEvent(final Object source,
                        final String aggregateIdentifier,
                        final String transaction) {
        super(source);
        this.aggregateIdentifier = aggregateIdentifier;
        this.transaction = transaction;
    }
}
