package com.dev.notification.app.auth.service.api.application;

import com.dev.notification.app.auth.service.api.infrastructure.security.jwt.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTokenFromCookieUseCase {
    private final JwtTokenUtils jwtTokenUtils;

    public String execute(final HttpServletRequest request){
        return jwtTokenUtils.getTokenFromCookie(request);
    }
}
