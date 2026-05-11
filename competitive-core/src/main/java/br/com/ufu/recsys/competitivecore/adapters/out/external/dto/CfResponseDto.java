package br.com.ufu.recsys.competitivecore.adapters.out.external.dto;

import java.util.List;

public record CfResponseDto<T>(
        String status,
        T result
) {
}
