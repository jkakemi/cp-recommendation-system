package br.com.ufu.recsys.academiccore.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class StudentProfile {
    private final UUID id;
    private final UUID userId;
    private final Platform platform;
    private String handle;
    private Integer currentRating;
    private String currentRank;
    private LocalDateTime lastSyncedAt;

    public StudentProfile(UUID id, UUID userId, String handle) {
        if(handle == null || handle.trim().isEmpty()){
            throw new IllegalArgumentException("Codeforces handle cannot be empty or null");
        }
        this.id = id;
        this.userId = userId;
        this.platform = Platform.CODEFORCES;
        this.handle = handle;
        this.lastSyncedAt = LocalDateTime.now();
    }

    public StudentProfile(UUID id, UUID userId, Platform platform, String handle, Integer currentRating, String currentRank, LocalDateTime lastSyncedAt) {
        this.id = id;
        this.userId = userId;
        this.platform = platform;
        this.handle = handle;
        this.currentRating = currentRating;
        this.currentRank = currentRank;
        this.lastSyncedAt = lastSyncedAt;
    }


    public void updateStats(Integer newRating, String newRank){
        this.currentRating = newRating;
        this.currentRank = newRank;
        this.lastSyncedAt = LocalDateTime.now();
    }

    public void updateHandle(String newHandle) {
        if (newHandle == null || newHandle.trim().isEmpty()) {
            throw new IllegalArgumentException("Codeforces handle cannot be empty or null");
        }
        this.handle = newHandle;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public Platform getPlatform() {
        return platform;
    }

    public String getHandle() {
        return handle;
    }

    public Integer getCurrentRating() {
        return currentRating;
    }

    public String getCurrentRank() {
        return currentRank;
    }

    public LocalDateTime getLastSyncedAt() {
        return lastSyncedAt;
    }
}
