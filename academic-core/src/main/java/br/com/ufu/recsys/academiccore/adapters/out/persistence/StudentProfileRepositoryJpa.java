package br.com.ufu.recsys.academiccore.adapters.out.persistence;

import br.com.ufu.recsys.academiccore.adapters.out.persistence.entity.StudentProfileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentProfileRepositoryJpa extends JpaRepository<StudentProfileJpaEntity, UUID>{
    Optional<StudentProfileJpaEntity> findByUserId(UUID userId);
    Optional<StudentProfileJpaEntity> findByHandle(String handle);
}
