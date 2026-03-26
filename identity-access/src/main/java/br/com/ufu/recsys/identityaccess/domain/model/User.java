package br.com.ufu.recsys.identityaccess.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private final UUID id;
    private String email;
    private String passwordHash;
    private boolean active;
    private final LocalDateTime createdAt;

    public User(UUID id, String email, String passwordHash) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public User(UUID id, String email, String passwordHash, boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.active = active;
        this.createdAt = createdAt;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public void changePassword(String newPassword) {
        if(newPassword == null || newPassword.trim().isEmpty()){
            throw new IllegalArgumentException("Password hash cannot be empty");
        }
        this.passwordHash = newPassword;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
