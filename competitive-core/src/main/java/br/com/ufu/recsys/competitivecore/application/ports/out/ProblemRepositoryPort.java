package br.com.ufu.recsys.competitivecore.application.ports.out;

import br.com.ufu.recsys.competitivecore.domain.model.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemRepositoryPort {
    void saveAll(List<Problem> problems);
    Optional<Problem> findByCfId(String cfId);
}