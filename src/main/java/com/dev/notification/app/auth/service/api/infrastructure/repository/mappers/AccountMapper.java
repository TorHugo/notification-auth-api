package com.dev.notification.app.auth.service.api.infrastructure.repository.mappers;

import com.dev.notification.app.auth.service.api.domain.entity.Account;
import com.dev.notification.app.auth.service.api.infrastructure.repository.models.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity toAggregate(final Account account){
        return new AccountEntity(
                account.getEmail().value(),
                account.getPassword(),
                account.isActive(),
                account.isAdmin(),
                account.isConfirmed(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                account.getLastAccess()
        );
    }

    public Account fromAggregate(final AccountEntity entity){
        return Account.restore(
                entity.getEmail(),
                entity.getPassword(),
                entity.isActive(),
                entity.isAdmin(),
                entity.isConfirmed(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getLastAccess()
        );
    }
}
