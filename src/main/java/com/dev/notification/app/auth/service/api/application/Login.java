package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.infrastructure.security.jwt.JwtTokenUtils;
import com.dev.notification.app.auth.service.api.infrastructure.security.models.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Login {
    private final AuthenticationManager authenticationManager;
    private final LastAccess lastAccess;
    private final JwtTokenUtils jwtTokenUtils;

    public ResponseCookie execute(final com.dev.notification.app.auth.service.api.infrastructure.api.models.Login value) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        value.email(),
                        value.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final var cookie = jwtTokenUtils.generateToken(userDetails);
        lastAccess.execute(value.email());
        return cookie;
    }
}
