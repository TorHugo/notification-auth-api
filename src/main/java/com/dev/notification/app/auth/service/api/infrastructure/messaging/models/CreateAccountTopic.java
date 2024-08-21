package com.dev.notification.app.auth.service.api.infrastructure.messaging.models;

import lombok.Data;

@Data
public class CreateAccountTopic {
    private String email;
    private String password;
    private boolean admin;
}