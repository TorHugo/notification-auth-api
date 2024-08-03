package com.dev.notification.app.auth.service.api.infrastructure.security.jwt;

import com.dev.notification.app.auth.service.api.application.FindAccountByEmail;
import com.dev.notification.app.auth.service.api.infrastructure.security.models.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final FindAccountByEmail findAccountByEmail;

    @Override
    public UserDetails loadUserByUsername(final String email){
        final var account = findAccountByEmail.execute(email);
        final var authorityAdminList = createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        final var authorityUserList = createAuthorityList("ROLE_USER");
        final var authority = account.isAdmin() ? authorityAdminList : authorityUserList;

        return UserDetailsImpl.build(account, authority);
    }
}
