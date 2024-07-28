package com.dev.notification.app.auth.service.api.domain.gateway;

import com.dev.notification.app.auth.service.api.domain.Account;

public interface AccountGateway {
    Account save(final Account value);
    Account findAccountByEmailWithThrows(final String value);
    Account findAccountByEmail(final String value);
}
