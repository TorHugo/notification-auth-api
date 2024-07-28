package com.dev.notification.app.auth.service.api.domain;

import com.dev.notification.app.auth.service.api.domain.value.object.Email;
import com.dev.notification.app.auth.service.api.domain.value.object.Password;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Account {

    private Email email;
    private Password password;
    private boolean active;
    private boolean admin;
    private boolean confirmed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastAccess;

    public Account(final String email,
                   final String password,
                   final boolean active,
                   final boolean admin,
                   final boolean confirmed,
                   final LocalDateTime createdAt,
                   final LocalDateTime updatedAt,
                   final LocalDateTime lastAccess) {
        this.email = new Email(email);
        this.password = new Password(password);
        this.active = active;
        this.admin = admin;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastAccess = lastAccess;
    }

    public static Account create(final String email,
                                 final String password,
                                 final boolean admin) {
        return new Account(email, password,true, admin,false, LocalDateTime.now(),null,null);
    }

    public static Account restore(final String email,
                                  final String password,
                                  final boolean active,
                                  final boolean admin,
                                  final boolean confirmed,
                                  final LocalDateTime createdAt,
                                  final LocalDateTime updatedAt,
                                  final LocalDateTime lastAccess){
        return new Account(email, password, active, admin, confirmed, createdAt, updatedAt, lastAccess);
    }

    public void setLastAccess() {
        this.lastAccess = LocalDateTime.now();
    }
}
