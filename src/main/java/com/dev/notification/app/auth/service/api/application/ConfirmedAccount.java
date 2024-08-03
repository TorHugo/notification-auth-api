package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.domain.exception.template.DomainException;
import com.dev.notification.app.auth.service.api.domain.gateway.AccountGateway;
import com.dev.notification.app.auth.service.api.infrastructure.messaging.models.ConfirmedAccountTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfirmedAccount {

    private final AccountGateway accountGateway;

    public void execute(final ConfirmedAccountTopic event){
        final var account = accountGateway.findAccountByEmailWithThrows(event.getEmail());
        if (account.isConfirmed()) throw new DomainException("This account is already confirmed!");
        account.isConfirmedAccount();
        accountGateway.save(account);
    }
}
