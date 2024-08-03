package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.domain.gateway.AccountGateway;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.ResetPasswordTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetPassword {
    private final AccountGateway accountGateway;
    public void execute(final ResetPasswordTopic event) {
        final var account = accountGateway.findAccountByEmailWithThrows(event.getEmail());
        account.updatePassword(event.getPassword());
        accountGateway.save(account);
    }
}
