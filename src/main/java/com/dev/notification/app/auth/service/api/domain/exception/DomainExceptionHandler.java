package com.dev.notification.app.auth.service.api.domain.exception;

public abstract class DomainExceptionHandler extends RuntimeException {
    private final String message;

    protected DomainExceptionHandler(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}