package br.com.ufu.recsys.competitivecore.adapters.out.external.dto;

import java.util.List;

public record CfProblemDto(
        Integer contestId,
        String index, // letter about problem A152, B233...
        String name,
        Integer rating,
        List<String> tags
) {
}
