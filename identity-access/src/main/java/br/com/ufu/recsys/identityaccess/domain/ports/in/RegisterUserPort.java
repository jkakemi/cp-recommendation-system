package br.com.ufu.recsys.identityaccess.domain.ports.in;

import br.com.ufu.recsys.identityaccess.domain.model.User;

public interface RegisterUserPort {
    User register(String email, String rawPassword);
}
