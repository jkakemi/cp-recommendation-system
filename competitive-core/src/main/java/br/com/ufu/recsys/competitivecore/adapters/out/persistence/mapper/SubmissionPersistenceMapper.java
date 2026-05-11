package br.com.ufu.recsys.competitivecore.adapters.out.persistence.mapper;

import br.com.ufu.recsys.competitivecore.adapters.out.persistence.entity.SubmissionJpaEntity;
import br.com.ufu.recsys.competitivecore.domain.model.Submission;
import org.springframework.stereotype.Component;

@Component
public class SubmissionPersistenceMapper {

    public SubmissionJpaEntity toJpaEntity(Submission domain) {
        return new SubmissionJpaEntity(
                domain.getId(),
                domain.getStudentId(),
                domain.getProblemId(),
                domain.getVerdict(),
                domain.getExecutionTimeMs(),
                domain.getSubmittedAt()
        );
    }

    public Submission toDomain(SubmissionJpaEntity entity) {
        return new Submission(
                entity.getId(),
                entity.getStudentId(),
                entity.getProblemId(),
                null,
                entity.getVerdict(),
                entity.getExecutionTimeMs(),
                entity.getSubmittedAt()
        );
    }
}