package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.domain.gateway.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LastAccessUseCase {
    private final AccountGateway accountGateway;

    public void execute(final String value) {
        final var account = accountGateway.findAccountByEmailWithThrows(value);
        account.updateLastAccess();
        accountGateway.save(account);
    }
}
