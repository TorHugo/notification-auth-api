package com.dev.notification.app.auth.service.api.infrastructure.event.models;

import com.dev.notification.app.auth.service.api.infrastructure.event.BaseEvent;
import lombok.Getter;

@Getter
public class ResetPasswordEvent extends BaseEvent {
    public ResetPasswordEvent(final Object source,
                              final String aggregateIdentifier,
                              final String transaction) {
        super(source, aggregateIdentifier, transaction);
    }
}
