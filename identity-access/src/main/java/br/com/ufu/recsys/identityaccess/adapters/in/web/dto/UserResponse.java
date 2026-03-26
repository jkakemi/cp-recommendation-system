package br.com.ufu.recsys.identityaccess.adapters.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        LocalDateTime createdAt
) {
}
