package br.com.ufu.recsys.academiccore.adapters.in.web.dto;

import java.util.UUID;

public record SyncProfileRequestDto(
        UUID userId,
        String handle
) {
}
