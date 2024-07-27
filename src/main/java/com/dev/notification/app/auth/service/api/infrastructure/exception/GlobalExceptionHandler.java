package com.dev.notification.app.auth.service.api.infrastructure.exception;

import com.dev.notification.app.auth.service.api.domain.exception.template.RepositoryException;
import com.dev.notification.app.auth.service.api.infrastructure.exception.models.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RepositoryException.class)
    public ExceptionResponse handleObjectIsNullOrEmptyError(final RepositoryException ex,
                                                            final HttpServletRequest request) {
        return ExceptionResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .status(INTERNAL_SERVER_ERROR.value())
                    .error(INTERNAL_SERVER_ERROR.getReasonPhrase())
                    .message(ex.getMessage())
                    .path(request.getRequestURI())
                .build();
    }
}
