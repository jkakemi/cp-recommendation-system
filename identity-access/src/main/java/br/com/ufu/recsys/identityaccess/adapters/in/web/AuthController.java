package br.com.ufu.recsys.identityaccess.adapters.in.web;

import br.com.ufu.recsys.identityaccess.adapters.in.web.dto.LoginRequest;
import br.com.ufu.recsys.identityaccess.adapters.in.web.dto.RegisterUserRequest;
import br.com.ufu.recsys.identityaccess.adapters.in.web.dto.TokenResponse;
import br.com.ufu.recsys.identityaccess.adapters.in.web.dto.UserResponse;
import br.com.ufu.recsys.identityaccess.domain.ports.in.AuthenticateUserPort;
import br.com.ufu.recsys.identityaccess.domain.ports.in.RegisterUserPort;
import br.com.ufu.recsys.identityaccess.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserPort registerUserPort;
    private final AuthenticateUserPort authenticateUserPort;

    public AuthController(RegisterUserPort registerUserPort, AuthenticateUserPort authenticateUserPort) {
        this.registerUserPort = registerUserPort;
        this.authenticateUserPort = authenticateUserPort;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        User registeredUser = registerUserPort.register(registerUserRequest.email(), registerUserRequest.password());

        UserResponse response = new UserResponse(
                registeredUser.getId(),
                registeredUser.getEmail(),
                registeredUser.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginRequest request){
        String token = authenticateUserPort.authenticate(request.email(), request.password());

        return ResponseEntity.ok(new TokenResponse(token));
    }
}
