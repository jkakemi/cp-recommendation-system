package br.com.ufu.recsys.competitivecore.adapters.out.persistence;

import br.com.ufu.recsys.competitivecore.adapters.out.persistence.mapper.ProblemPersistenceMapper;
import br.com.ufu.recsys.competitivecore.application.ports.out.ProblemRepositoryPort;
import br.com.ufu.recsys.competitivecore.domain.model.Problem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProblemRepositoryAdapter implements ProblemRepositoryPort {

    private final SpringDataProblemRepository springRepository;
    private final ProblemPersistenceMapper mapper;

    public ProblemRepositoryAdapter(SpringDataProblemRepository springRepository, ProblemPersistenceMapper mapper) {
        this.springRepository = springRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveAll(List<Problem> problems) {
        var entities = problems.stream().map(mapper::toJpaEntity).toList();
        springRepository.saveAll(entities);
    }

    @Override
    public Optional<Problem> findByCfId(String cfId) {
        return springRepository.findByCfProblemId(cfId)
                .map(mapper::toDomain);
    }
}