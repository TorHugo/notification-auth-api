package com.dev.notification.app.auth.service.api.infrastructure.gateway;

import com.dev.notification.app.auth.service.api.domain.Account;
import com.dev.notification.app.auth.service.api.domain.exception.template.RepositoryException;
import com.dev.notification.app.auth.service.api.domain.gateway.AccountGateway;
import com.dev.notification.app.auth.service.api.infrastructure.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountGatewayImpl implements AccountGateway {
    private final AccountRepository accountRepository;

    @Override
    public void save(final Account value) {
        accountRepository.save(value);
    }

    @Override
    public Account findAccountByEmailWithThrows(final String value) {
        final var account = accountRepository.findAccountByEmail(value);
        if (Objects.isNull(account)) throw new RepositoryException("Account not found!");
        return account;
    }

    @Override
    public Account findAccountByEmail(final String value) {
        return accountRepository.findAccountByEmail(value);
    }
}
