package br.com.ufu.recsys.academiccore.adapters.out.persistence.entity;

import br.com.ufu.recsys.academiccore.domain.model.Platform;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "student_profiles")
@Getter
@AllArgsConstructor
public class StudentProfileJpaEntity {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Platform platform;

    @Column(nullable = false, unique = true)
    private String handle;

    @Column(name = "current_rating")
    private Integer currentRating;

    @Column(name = "current_rank")
    private String currentRank;

    @Column(name = "last_synced_at")
    private LocalDateTime lastSyncedAt;

    protected StudentProfileJpaEntity() {}
}