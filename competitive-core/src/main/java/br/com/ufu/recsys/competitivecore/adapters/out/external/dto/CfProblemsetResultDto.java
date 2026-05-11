package br.com.ufu.recsys.competitivecore.adapters.out.external.dto;

import java.util.List;

public record CfProblemsetResultDto(
        List<CfProblemDto> problems
) {}
