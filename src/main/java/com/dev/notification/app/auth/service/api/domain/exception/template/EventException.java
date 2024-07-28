package com.dev.notification.app.auth.service.api.domain.exception.template;

import com.dev.notification.app.auth.service.api.domain.exception.DomainExceptionHandler;

public class EventException extends DomainExceptionHandler {
    public EventException(final String message) {
        super(message);
    }
}
