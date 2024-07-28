package com.dev.notification.app.auth.service.api.domain.exception.template;

import com.dev.notification.app.auth.service.api.domain.exception.DomainExceptionHandler;

public class DomainException extends DomainExceptionHandler {
    public DomainException(final String message, final String parameter) {
        super(message.concat(" | ").concat(parameter));
    }

    public DomainException(final String message) {
        super(message);
    }
}
