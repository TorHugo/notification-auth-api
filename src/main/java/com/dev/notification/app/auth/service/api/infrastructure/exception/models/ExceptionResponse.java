package com.dev.notification.app.auth.service.api.infrastructure.exception.models;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path) {
}
