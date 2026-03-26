package br.com.ufu.recsys.academiccore.adapters.out.persistence;

import br.com.ufu.recsys.academiccore.adapters.out.persistence.entity.StudentProfileJpaEntity;
import br.com.ufu.recsys.academiccore.domain.model.StudentProfile;
import org.springframework.stereotype.Component;

@Component
public class StudentProfileMapper {

    public StudentProfileJpaEntity toJpaEntity(StudentProfile domain) {
        if (domain == null) return null;

        return new StudentProfileJpaEntity(
                domain.getId(),
                domain.getUserId(),
                domain.getPlatform(),
                domain.getHandle(),
                domain.getCurrentRating(),
                domain.getCurrentRank(),
                domain.getLastSyncedAt()
        );
    }

    public StudentProfile toDomain(StudentProfileJpaEntity entity) {
        if (entity == null) return null;

        return new StudentProfile(
                entity.getId(),
                entity.getUserId(),
                entity.getPlatform(),
                entity.getHandle(),
                entity.getCurrentRating(),
                entity.getCurrentRank(),
                entity.getLastSyncedAt()
        );
    }
}