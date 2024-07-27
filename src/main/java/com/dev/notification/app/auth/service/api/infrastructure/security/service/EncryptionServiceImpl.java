package com.dev.notification.app.auth.service.api.infrastructure.security.service;

import com.dev.notification.app.auth.service.api.domain.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encryption(final String value) {
        return passwordEncoder.encode(value);
    }
}
