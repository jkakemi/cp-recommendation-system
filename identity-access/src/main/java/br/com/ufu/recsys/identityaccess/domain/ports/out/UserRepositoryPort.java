package br.com.ufu.recsys.identityaccess.domain.ports.out;

import br.com.ufu.recsys.identityaccess.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
}
