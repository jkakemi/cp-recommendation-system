package br.com.ufu.recsys.identityaccess.adapters.out.persistence;

import br.com.ufu.recsys.identityaccess.adapters.out.persistence.entity.UserJpaEntity;
import br.com.ufu.recsys.identityaccess.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserJpaEntity toJpaEntity(User domain){
        if(domain == null) return null;

        return new UserJpaEntity(
                domain.getId(),
                domain.getEmail(),
                domain.getPasswordHash(),
                domain.isActive(),
                domain.getCreatedAt()
        );
    }

    public User toDomain(UserJpaEntity entity){
        if(entity == null) return null;

        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.isActive(),
                entity.getCreatedAt()
        );
    }
}
