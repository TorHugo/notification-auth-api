package com.dev.notification.app.auth.service.api.infrastructure.gateway;

import com.dev.notification.app.auth.service.api.domain.Account;
import com.dev.notification.app.auth.service.api.domain.exception.template.RepositoryException;
import com.dev.notification.app.auth.service.api.domain.gateway.AccountGateway;
import com.dev.notification.app.auth.service.api.infrastructure.repository.AccountRepository;
import com.dev.notification.app.auth.service.api.infrastructure.repository.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountGatewayImpl implements AccountGateway {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account save(final Account value) {
        final var entity = accountMapper.toAggregate(value);
        accountRepository.save(entity);
        return value;
    }

    @Override
    public Account findAccountByEmailWithThrows(final String value) {
        final var entity = accountRepository.findAccountByEmail(value);
        if (Objects.isNull(entity)) throw new RepositoryException("Account not found!");
        return accountMapper.fromAggregate(entity);
    }

    @Override
    public Account findAccountByEmail(final String value) {
        final var entity = accountRepository.findAccountByEmail(value);
        return Objects.isNull(entity) ? null : accountMapper.fromAggregate(entity);
    }
}
