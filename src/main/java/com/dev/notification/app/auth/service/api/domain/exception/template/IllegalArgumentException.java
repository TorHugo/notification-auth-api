package com.dev.notification.app.auth.service.api.domain.exception.template;

import com.dev.notification.app.auth.service.api.domain.exception.DomainExceptionHandler;

public class IllegalArgumentException extends DomainExceptionHandler {
    public IllegalArgumentException(final String message) {
        super(message);
    }
}
