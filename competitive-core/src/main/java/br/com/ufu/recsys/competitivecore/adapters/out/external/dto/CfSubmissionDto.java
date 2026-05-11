package br.com.ufu.recsys.competitivecore.adapters.out.external.dto;

public record CfSubmissionDto(
        Long id,
        Long creationTimeSeconds,
        Integer timeConsumedMillis,
        String verdict,
        CfProblemDto problem
) {
}
