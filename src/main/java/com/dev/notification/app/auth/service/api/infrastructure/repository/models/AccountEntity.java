package com.dev.notification.app.auth.service.api.infrastructure.repository.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Table(name = "account_tb", schema = "auth_service_db")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    private String email;
    private String password;
    private boolean active;
    private boolean admin;
    private boolean confirmed;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    private LocalDateTime lastAccess;
}
