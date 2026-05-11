package br.com.ufu.recsys.competitivecore.adapters.out.external;

import br.com.ufu.recsys.competitivecore.adapters.out.external.dto.CfProblemDto;
import br.com.ufu.recsys.competitivecore.adapters.out.external.dto.CfProblemsetResultDto;
import br.com.ufu.recsys.competitivecore.adapters.out.external.dto.CfResponseDto;
import br.com.ufu.recsys.competitivecore.adapters.out.external.dto.CfSubmissionDto;
import br.com.ufu.recsys.competitivecore.application.ports.out.CodeforcesApiPort;
import br.com.ufu.recsys.competitivecore.domain.model.Problem;
import br.com.ufu.recsys.competitivecore.domain.model.Submission;
import br.com.ufu.recsys.competitivecore.domain.model.Verdict;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Component("competitiveCodeforcesHttpAdapter")
public class CodeforcesHttpAdapter implements CodeforcesApiPort {

    private final RestClient restClient;

    public CodeforcesHttpAdapter() {
        this.restClient = RestClient.builder()
                .baseUrl("https://codeforces.com/api")
                .build();
    }

    @Override
    public List<Problem> fetchAllProblems() {
        var responseType = new ParameterizedTypeReference<CfResponseDto<CfProblemsetResultDto>>() {};

        try {
            var response = restClient.get()
                    .uri("/problemset.problems")
                    .retrieve()
                    .body(responseType);

            if (response != null && "OK".equals(response.status()) && response.result() != null) {

                return response.result().problems().stream()
                        .map(dto -> new Problem(
                                UUID.randomUUID(),
                                dto.contestId() + dto.index(),
                                dto.name(),
                                dto.rating(),
                                dto.tags()
                        )).toList();
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar problemas: " + e.getMessage());
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Submission> fetchUserSubmissions(UUID studentId, String handle) {
        var responseType = new ParameterizedTypeReference<CfResponseDto<List<CfSubmissionDto>>>() {};

        try {
            var response = restClient.get()
                    .uri("/user.status?handle={handle}", handle)
                    .retrieve()
                    .body(responseType);

            if (response != null && "OK".equals(response.status()) && response.result() != null) {
                return response.result().stream()
                        .map(dto -> {
                            String cfProblemId = (dto.problem() != null && dto.problem().contestId() != null)
                                    ? dto.problem().contestId() + dto.problem().index()
                                    : null;

                            return new Submission(
                                    UUID.randomUUID(),
                                    studentId,
                                    null,
                                    cfProblemId,
                                    mapVerdict(dto.verdict()),
                                    dto.timeConsumedMillis(),
                                    LocalDateTime.ofInstant(Instant.ofEpochSecond(dto.creationTimeSeconds()), ZoneId.systemDefault())
                            );
                        }).toList();
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar submissões do usuário " + handle + ": " + e.getMessage());
        }
        return List.of();
    }

    private Verdict mapVerdict(String cfVerdict) {
        if (cfVerdict == null) return Verdict.UNKNOWN;
        return switch (cfVerdict) {
            case "OK" -> Verdict.OK;
            case "WRONG_ANSWER" -> Verdict.WRONG_ANSWER;
            case "TIME_LIMIT_EXCEEDED" -> Verdict.TIME_LIMIT_EXCEEDED;
            case "MEMORY_LIMIT_EXCEEDED" -> Verdict.MEMORY_LIMIT_EXCEEDED;
            case "COMPILATION_ERROR" -> Verdict.COMPILATION_ERROR;
            case "RUNTIME_ERROR" -> Verdict.RUNTIME_ERROR;
            default -> Verdict.UNKNOWN;
        };
    }
}