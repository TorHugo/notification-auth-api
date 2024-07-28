package com.dev.notification.app.auth.service.api.infrastructure.repository.models;

import com.dev.notification.app.auth.service.api.domain.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static org.hibernate.type.SqlTypes.JSON;

@Table(name = "events_tb", schema = "auth_service_db")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class EventEntity {
    @Id
    private String identifier;
    private String aggregateIdentifier;

    @JdbcTypeCode(JSON)
    private String transaction;

    @Enumerated(STRING)
    private EventType eventType;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
