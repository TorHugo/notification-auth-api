package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.domain.entity.Account;
import com.dev.notification.app.auth.service.api.domain.exception.template.DomainException;
import com.dev.notification.app.auth.service.api.domain.gateway.AccountGateway;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.CreateAccountTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SaveAccountUseCase {
    private final AccountGateway accountGateway;

    public Account execute(final CreateAccountTopic saveAccount){
        final var existsAccount = accountGateway.findAccountByEmail(saveAccount.getEmail());
        if (Objects.nonNull(existsAccount)) throw new DomainException("This account already exists!");
        final var account = Account.create(saveAccount.getEmail(), saveAccount.getPassword(), saveAccount.isAdmin());
        return accountGateway.save(account);
    }
}
