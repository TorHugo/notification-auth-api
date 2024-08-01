package com.dev.notification.app.auth.service.api.domain.exception.template;

import com.dev.notification.app.auth.service.api.domain.exception.DomainExceptionHandler;

public class ListenerException extends DomainExceptionHandler {
    public ListenerException(final String message) {
        super(message);
    }
}
