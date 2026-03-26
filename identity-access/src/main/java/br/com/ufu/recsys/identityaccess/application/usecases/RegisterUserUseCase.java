package br.com.ufu.recsys.identityaccess.application.usecases;

import br.com.ufu.recsys.identityaccess.domain.ports.out.PasswordEncoderPort;
import br.com.ufu.recsys.identityaccess.domain.ports.out.UserRepositoryPort;
import br.com.ufu.recsys.identityaccess.domain.model.User;
import br.com.ufu.recsys.identityaccess.domain.ports.in.RegisterUserPort;

import java.util.UUID;

public class RegisterUserUseCase implements RegisterUserPort {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public RegisterUserUseCase(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public User register(String email, String rawPassword) {
        if(userRepositoryPort.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("Email already in use");
        }

        String encodedPassword = passwordEncoderPort.encode(rawPassword);
        User newUser = new User(UUID.randomUUID(), email, encodedPassword);

        return userRepositoryPort.save(newUser);

    }
}
