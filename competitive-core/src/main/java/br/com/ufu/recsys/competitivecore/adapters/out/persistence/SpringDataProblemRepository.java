package br.com.ufu.recsys.competitivecore.adapters.out.persistence;

import br.com.ufu.recsys.competitivecore.adapters.out.persistence.entity.ProblemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataProblemRepository extends JpaRepository<ProblemJpaEntity, UUID> {
    Optional<ProblemJpaEntity> findByCfProblemId(String cfProblemId);
}
