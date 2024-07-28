package com.dev.notification.app.auth.service.api.infrastructure.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Date;

@Builder
public record ValidToken(
        @JsonProperty("is_valid") boolean isValid,
        @JsonProperty("email") String email,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
        @JsonProperty("expiration_date") Date expirationDate,
        @JsonProperty("roles") Object roles
) {
}
