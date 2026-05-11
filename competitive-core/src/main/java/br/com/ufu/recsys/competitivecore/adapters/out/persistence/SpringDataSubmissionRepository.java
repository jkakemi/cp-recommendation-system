package br.com.ufu.recsys.competitivecore.adapters.out.persistence;

import br.com.ufu.recsys.competitivecore.adapters.out.persistence.entity.SubmissionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataSubmissionRepository extends JpaRepository<SubmissionJpaEntity, UUID> {
    List<SubmissionJpaEntity> findByStudentId(UUID studentId);
}