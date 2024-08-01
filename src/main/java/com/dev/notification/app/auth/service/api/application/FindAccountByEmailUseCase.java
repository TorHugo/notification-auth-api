package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.domain.entity.Account;
import com.dev.notification.app.auth.service.api.domain.gateway.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAccountByEmailUseCase {
    private final AccountGateway accountGateway;
    public Account execute(final String email) {
        return accountGateway.findAccountByEmailWithThrows(email);
    }
}
