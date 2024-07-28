package com.dev.notification.app.auth.service.api.infrastructure.api.controller;

import com.dev.notification.app.auth.service.api.application.GetTokenFromCookieUseCase;
import com.dev.notification.app.auth.service.api.application.LoginUseCase;
import com.dev.notification.app.auth.service.api.application.LogoutUseCase;
import com.dev.notification.app.auth.service.api.application.ValidateTokenUseCase;
import com.dev.notification.app.auth.service.api.infrastructure.api.AuthAPI;
import com.dev.notification.app.auth.service.api.infrastructure.api.models.AuthSuccess;
import com.dev.notification.app.auth.service.api.infrastructure.api.models.Login;
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
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final GetTokenFromCookieUseCase getTokenFromCookieUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;

    @Override
    public ResponseEntity<?> login(final Login request) {
        final var token = loginUseCase.execute(request);
        return ok().header(SET_COOKIE, token.toString()).body(new AuthSuccess("Login with successfully!"));
    }

    @Override
    public ResponseEntity<?> logout() {
        return ok().header(SET_COOKIE, logoutUseCase.execute().toString()).body(new AuthSuccess("Logout with successfully!"));
    }

    @Override
    public ValidToken validate(final HttpServletRequest request) {
        final var token = getTokenFromCookieUseCase.execute(request);
        return validateTokenUseCase.execute(token);
    }
}
