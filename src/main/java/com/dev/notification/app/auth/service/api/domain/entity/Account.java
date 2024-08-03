package com.dev.notification.app.auth.service.api.domain.entity;

import com.dev.notification.app.auth.service.api.domain.value.object.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Account {

    private final Email email;
    private String password;
    private final boolean active;
    private final boolean admin;
    private boolean confirmed;
    private final LocalDateTime createdAt;
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
        this.password = password;
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

    public void updateLastAccess() {
        this.lastAccess = LocalDateTime.now();
    }

    public void isConfirmedAccount(){
        this.confirmed = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void updatePassword(final String password) {
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }
}
