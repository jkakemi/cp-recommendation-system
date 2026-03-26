package br.com.ufu.recsys.academiccore.adapters.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProfileResponseDto(
        UUID id,
        UUID userId,
        String platform,
        String handle,
        Integer currentRating,
        String currentRank,
        LocalDateTime lastSyncedAt
) {
}
