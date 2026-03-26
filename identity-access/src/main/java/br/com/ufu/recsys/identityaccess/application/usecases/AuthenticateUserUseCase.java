package br.com.ufu.recsys.identityaccess.application.usecases;

import br.com.ufu.recsys.identityaccess.domain.ports.in.AuthenticateUserPort;
import br.com.ufu.recsys.identityaccess.domain.ports.out.JwtPort;
import br.com.ufu.recsys.identityaccess.domain.ports.out.PasswordEncoderPort;
import br.com.ufu.recsys.identityaccess.domain.ports.out.UserRepositoryPort;
import br.com.ufu.recsys.identityaccess.domain.model.User;

public class AuthenticateUserUseCase implements AuthenticateUserPort {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final JwtPort jwtPort;

    public AuthenticateUserUseCase(UserRepositoryPort userRepositoryPort,
                                   PasswordEncoderPort passwordEncoderPort,
                                   JwtPort jwtPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.jwtPort = jwtPort;
    }

    @Override
    public String authenticate(String email, String rawPassword) {
        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials provided"));

        if (!passwordEncoderPort.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials provided");
        }

        return jwtPort.generateToken(user);
    }
}