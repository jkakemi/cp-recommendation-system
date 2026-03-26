package br.com.ufu.recsys.identityaccess.domain.ports.out;

import br.com.ufu.recsys.identityaccess.domain.model.User;

public interface JwtPort {
    String generateToken(User user);
    String getSubjectFromToken(String token);
}