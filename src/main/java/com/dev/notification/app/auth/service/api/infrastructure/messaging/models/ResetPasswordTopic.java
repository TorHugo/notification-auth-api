package com.dev.notification.app.auth.service.api.infrastructure.messaging.models;

import lombok.Data;

@Data
public class ResetPasswordTopic {
    private String email;
    private String password;
}
