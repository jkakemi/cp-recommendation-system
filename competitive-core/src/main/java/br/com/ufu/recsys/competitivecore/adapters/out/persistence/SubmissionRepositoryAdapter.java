package br.com.ufu.recsys.competitivecore.adapters.out.persistence;

import br.com.ufu.recsys.competitivecore.adapters.out.persistence.mapper.SubmissionPersistenceMapper;
import br.com.ufu.recsys.competitivecore.application.ports.out.SubmissionRepositoryPort;
import br.com.ufu.recsys.competitivecore.domain.model.Submission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubmissionRepositoryAdapter implements SubmissionRepositoryPort {

    private final SpringDataSubmissionRepository springRepository;
    private final SubmissionPersistenceMapper mapper;

    public SubmissionRepositoryAdapter(SpringDataSubmissionRepository springRepository, SubmissionPersistenceMapper mapper) {
        this.springRepository = springRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveAll(List<Submission> submissions) {
        var entities = submissions.stream().map(mapper::toJpaEntity).toList();
        springRepository.saveAll(entities);
    }
}
