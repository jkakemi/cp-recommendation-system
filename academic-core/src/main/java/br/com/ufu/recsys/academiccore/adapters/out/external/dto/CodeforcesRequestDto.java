package br.com.ufu.recsys.academiccore.adapters.out.external.dto;

public record CodeforcesRequestDto(
        String handle,
        Integer rating,
        String rank
) {
}
