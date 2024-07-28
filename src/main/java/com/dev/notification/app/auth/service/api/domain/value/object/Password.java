package com.dev.notification.app.auth.service.api.domain.value.object;

import com.dev.notification.app.auth.service.api.domain.exception.template.DomainException;

import java.util.Objects;

public record Password(String value) implements ValueObject<String> {

    public Password(final String value) {
        validate(value);
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public void validate(final String currentValue) {
        if (currentValue.length() < 8)
            throw new DomainException("This password must be at least 8 characters.");
        if (!currentValue.matches(".*[A-Z].*"))
            throw new DomainException("This password does not contain uppercase letters.");
        if (!currentValue.matches(".*[a-z].*"))
            throw new DomainException("This password does not contain lowercase letters.");
        if (!currentValue.matches(".*\\W.*"))
            throw new DomainException("This password must have a special character.");
    }
}
