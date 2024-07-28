package com.dev.notification.app.auth.service.api.domain.exception.template;

import com.dev.notification.app.auth.service.api.domain.exception.DomainExceptionHandler;

public class RepositoryException extends DomainExceptionHandler {
    public RepositoryException(final String message) {
        super(message);
    }
}