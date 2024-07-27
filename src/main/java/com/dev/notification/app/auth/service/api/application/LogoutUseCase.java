package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.infrastructure.security.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutUseCase {
    private final JwtTokenUtils jwtTokenUtils;

    public ResponseCookie execute(){
        return this.jwtTokenUtils.cleanToken();
    }
}
