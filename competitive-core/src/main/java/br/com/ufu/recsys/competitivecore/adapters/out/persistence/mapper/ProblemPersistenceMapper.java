package br.com.ufu.recsys.competitivecore.adapters.out.persistence.mapper;

import br.com.ufu.recsys.competitivecore.adapters.out.persistence.entity.ProblemJpaEntity;
import br.com.ufu.recsys.competitivecore.domain.model.Problem;
import org.springframework.stereotype.Component;

@Component
public class ProblemPersistenceMapper {

    public ProblemJpaEntity toJpaEntity(Problem domain) {
        return new ProblemJpaEntity(
                domain.getId(),
                domain.getCfProblemId(),
                domain.getTitle(),
                domain.getRating(),
                domain.getTags()
        );
    }

    public Problem toDomain(ProblemJpaEntity entity) {
        return new Problem(
                entity.getId(),
                entity.getCfProblemId(),
                entity.getTitle(),
                entity.getRating(),
                entity.getTags()
        );
    }
}