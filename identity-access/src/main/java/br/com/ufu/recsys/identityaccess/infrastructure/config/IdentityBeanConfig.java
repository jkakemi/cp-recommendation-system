package br.com.ufu.recsys.identityaccess.infrastructure.config;

import br.com.ufu.recsys.identityaccess.domain.ports.in.AuthenticateUserPort;
import br.com.ufu.recsys.identityaccess.domain.ports.in.RegisterUserPort;
import br.com.ufu.recsys.identityaccess.domain.ports.out.JwtPort;
import br.com.ufu.recsys.identityaccess.domain.ports.out.PasswordEncoderPort;
import br.com.ufu.recsys.identityaccess.domain.ports.out.UserRepositoryPort;
import br.com.ufu.recsys.identityaccess.application.usecases.AuthenticateUserUseCase;
import br.com.ufu.recsys.identityaccess.application.usecases.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IdentityBeanConfig {
    @Bean
    public RegisterUserPort registerUserUseCase(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoderPort passwordEncoderPort) {
        return new RegisterUserUseCase(userRepositoryPort, passwordEncoderPort);
    }

    @Bean
    public AuthenticateUserPort authenticateUserPort(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoderPort passwordEncoderPort,
            JwtPort jwtPort) {
        return new AuthenticateUserUseCase(userRepositoryPort, passwordEncoderPort, jwtPort);
    }
}
