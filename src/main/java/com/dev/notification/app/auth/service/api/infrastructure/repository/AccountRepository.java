package com.dev.notification.app.auth.service.api.infrastructure.repository;

import com.dev.notification.app.auth.service.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findAccountByEmail(final String email);
}
