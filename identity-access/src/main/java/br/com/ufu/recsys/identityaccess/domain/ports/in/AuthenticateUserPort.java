package br.com.ufu.recsys.identityaccess.domain.ports.in;

public interface AuthenticateUserPort {
    String authenticate(String email, String rawPassword);
}
