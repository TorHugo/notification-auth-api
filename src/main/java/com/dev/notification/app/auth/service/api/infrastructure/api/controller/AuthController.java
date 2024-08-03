package com.dev.notification.app.auth.service.api.infrastructure.api.controller;

import com.dev.notification.app.auth.service.api.application.GetTokenFromCookie;
import com.dev.notification.app.auth.service.api.application.Login;
import com.dev.notification.app.auth.service.api.application.Logout;
import com.dev.notification.app.auth.service.api.application.ValidateToken;
import com.dev.notification.app.auth.service.api.infrastructure.api.AuthAPI;
import com.dev.notification.app.auth.service.api.infrastructure.api.models.AuthSuccess;
import com.dev.notification.app.auth.service.api.infrastructure.api.models.ValidToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthAPI {
    private final Login login;
    private final Logout logout;
    private final GetTokenFromCookie getTokenFromCookie;
    private final ValidateToken validateToken;

    @Override
    public ResponseEntity<?> login(final com.dev.notification.app.auth.service.api.infrastructure.api.models.Login request) {
        final var token = login.execute(request);
        return ok().header(SET_COOKIE, token.toString()).body(new AuthSuccess("Login with successfully!"));
    }

    @Override
    public ResponseEntity<?> logout() {
        return ok().header(SET_COOKIE, logout.execute().toString()).body(new AuthSuccess("Logout with successfully!"));
    }

    @Override
    public ValidToken validate(final HttpServletRequest request) {
        final var token = getTokenFromCookie.execute(request);
        return validateToken.execute(token);
    }
}
