package com.dev.notification.app.auth.service.api.domain.value.object;

public interface ValueObject<T> {
    void validate(final T value);
    T value();
}
