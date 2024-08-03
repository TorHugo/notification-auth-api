package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.infrastructure.api.models.ValidToken;
import com.dev.notification.app.auth.service.api.infrastructure.security.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateToken {
    private final JwtTokenUtils jwtTokenUtils;

    public ValidToken execute(final String token){
        final var isTokenValid = jwtTokenUtils.validateToken(token);
        final var username = jwtTokenUtils.getUserName(token);
        final var expirationDate = jwtTokenUtils.getExpirationDate(token);
        final var roles = jwtTokenUtils.getAuthoritiesFromToken(token);

        return ValidToken.builder()
                .isValid(isTokenValid)
                .email(username)
                .expirationDate(expirationDate)
                .roles(roles)
                .build();
    }
}
