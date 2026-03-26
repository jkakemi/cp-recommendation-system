package br.com.ufu.recsys.identityaccess.adapters.out.persistence;

import br.com.ufu.recsys.identityaccess.domain.ports.out.UserRepositoryPort;
import br.com.ufu.recsys.identityaccess.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final SpringDataJpaUserRepository springDataJpaUserRepository;
    private final UserMapper userMapper;

    public UserRepositoryAdapter(SpringDataJpaUserRepository springDataJpaUserRepository, UserMapper userMapper) {
        this.springDataJpaUserRepository = springDataJpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        var entity = userMapper.toJpaEntity(user);
        var savedEntity = springDataJpaUserRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataJpaUserRepository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springDataJpaUserRepository.findById(id).map(userMapper::toDomain);
    }
}
