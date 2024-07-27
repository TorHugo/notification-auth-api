package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.domain.Account;
import com.dev.notification.app.auth.service.api.domain.exception.template.IllegalArgumentException;
import com.dev.notification.app.auth.service.api.domain.gateway.AccountGateway;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.CreateAccountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SaveAccountUseCase {
    private final AccountGateway accountGateway;

    public void execute(final CreateAccountEvent saveAccount){
        final var existsAccount = accountGateway.findAccountByEmail(saveAccount.getEmail());
        if (Objects.nonNull(existsAccount)) throw new IllegalArgumentException("This account already exists!");
        final var account = Account.create(saveAccount.getEmail(), saveAccount.getPassword(), saveAccount.isAdmin());
        accountGateway.save(account);
    }
}
