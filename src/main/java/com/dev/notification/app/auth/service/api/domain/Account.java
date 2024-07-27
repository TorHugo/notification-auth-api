package com.dev.notification.app.auth.service.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "account_tb", schema = "auth_service_db")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String email;
    private String password;
    private boolean active;
    private boolean admin;
    private boolean confirmed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastAccess;

    public void setLastAccess() {
        this.lastAccess = LocalDateTime.now();
    }

    public static Account create(final String email, final String password, final boolean admin) {
        return new Account(email, password, true, admin, false, LocalDateTime.now(), null, LocalDateTime.now());
    }
}
