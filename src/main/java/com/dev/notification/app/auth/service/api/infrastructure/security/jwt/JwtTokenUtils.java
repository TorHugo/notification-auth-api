package com.dev.notification.app.auth.service.api.infrastructure.security.jwt;

import com.dev.notification.app.auth.service.api.infrastructure.security.models.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.parser;
import static org.springframework.http.ResponseCookie.from;
import static org.springframework.web.util.WebUtils.getCookie;

@Component
@Slf4j
public class JwtTokenUtils {
    private static final String COOKIE = "token";

    @Value("${spring.security.security-key}")
    private String secret;

    @Value("${spring.security.token-expiration}")
    private Integer expiration;

    public String getTokenFromCookie(final HttpServletRequest request) {
        final var cookie = getCookie(request, COOKIE);
        return Objects.isNull(cookie) ? null : cookie.getValue();
    }

    public boolean validateToken(final String token) {
        try {
            parser()
                    .verifyWith(getSigningKey(secret))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (final WeakKeyException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (final MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (final ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (final UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (final IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUserName(final String token) {
        return parser()
                .verifyWith(getSigningKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public ResponseCookie generateToken(final UserDetailsImpl user) {
        final var token = generateTokenFromUser(user);
        return from(COOKIE, token)
                .path("/")
                .maxAge(216_000)
                .secure(true)
                .httpOnly(true)
                .build();
    }

    public ResponseCookie cleanToken(){
        return from(COOKIE, "")
                .path("/")
                .maxAge(0)
                .secure(true)
                .httpOnly(true)
                .build();
    }

    private String generateTokenFromUser(final UserDetailsImpl user) {
        if (!user.isActive())
            throw new AccessDeniedException("Account Expired!");
        final var dateNow = new Date();
        final var timeOfExpiration  = expiration;

        return builder()
                .claim("email", user.getEmail())
                .claim("confirmed", user.isConfirmed())
                .claim("active", user.isActive())
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(dateNow.getTime() + timeOfExpiration))
                .signWith(getSigningKey(secret))
                .compact();
    }

    private SecretKey getSigningKey(final String secret){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
