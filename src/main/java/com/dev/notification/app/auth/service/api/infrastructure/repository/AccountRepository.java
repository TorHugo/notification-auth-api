package com.dev.notification.app.auth.service.api.infrastructure.repository;

import com.dev.notification.app.auth.service.api.infrastructure.repository.models.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    AccountEntity findAccountByEmail(final String email);
}
