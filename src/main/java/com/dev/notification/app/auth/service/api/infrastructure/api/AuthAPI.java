package com.dev.notification.app.auth.service.api.infrastructure.api;

import com.dev.notification.app.auth.service.api.infrastructure.api.models.Login;
import com.dev.notification.app.auth.service.api.infrastructure.api.models.ValidToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(value = "/auth")
public interface AuthAPI {
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> login(final @Valid @RequestBody Login request);

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> logout();

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    ValidToken validate(final HttpServletRequest request);
}
