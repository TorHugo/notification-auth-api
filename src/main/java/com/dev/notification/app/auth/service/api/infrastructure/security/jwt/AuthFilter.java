package com.dev.notification.app.auth.service.api.infrastructure.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        try {
            final var webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();
            final var token = jwtTokenUtils.getTokenFromCookie(request);

            if (Objects.nonNull(token) && jwtTokenUtils.validateToken(token)){
                final var userName = jwtTokenUtils.getUserName(token);
                final var loadedUser = userDetailsServiceImpl.loadUserByUsername(userName);

                if (!loadedUser.isEnabled())
                    throw new AccessDeniedException("User Disabled!");
                // TODO: Validate to confirmed user.

                final var authentication = new UsernamePasswordAuthenticationToken(
                        loadedUser,
                        null,
                        loadedUser.getAuthorities()
                );
                authentication.setDetails(webAuthenticationDetailsSource.buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (final Exception e) {
            log.error("Cannot set user authentication: {}.", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
