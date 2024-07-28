package com.dev.notification.app.auth.service.api.domain.value.object;

import com.dev.notification.app.auth.service.api.domain.exception.template.DomainException;

import java.util.Objects;

public record Email(String value) implements ValueObject<String> {

    public Email(final String value) {
        validate(value);
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public void validate(final String currentValue) {
        if (isValidValue(currentValue))
            throw new DomainException("Invalid e-mail.", currentValue);
    }

    private boolean isValidValue(final String email) {
        return !email.matches("^(.+)@(.+)$");
    }
}
