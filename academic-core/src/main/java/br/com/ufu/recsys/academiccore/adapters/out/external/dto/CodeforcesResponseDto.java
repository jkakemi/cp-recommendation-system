package br.com.ufu.recsys.academiccore.adapters.out.external.dto;

import java.util.List;

public record CodeforcesResponseDto(
        String status,
        List<CodeforcesRequestDto> result
) {
}
