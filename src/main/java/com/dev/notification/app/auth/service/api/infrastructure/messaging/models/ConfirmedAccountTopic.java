package com.dev.notification.app.auth.service.api.infrastructure.messaging.models;

import lombok.Data;

@Data
public class ConfirmedAccountTopic {
    private String email;
    private boolean confirmed;
}
