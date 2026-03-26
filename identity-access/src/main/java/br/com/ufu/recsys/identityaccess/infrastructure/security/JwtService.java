package br.com.ufu.recsys.identityaccess.infrastructure.security;

import br.com.ufu.recsys.identityaccess.domain.ports.out.JwtPort;
import br.com.ufu.recsys.identityaccess.domain.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JwtService implements JwtPort {

    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    public String generateToken(User user){
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("codeforces-recsys")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error generating JWT Token", exception);
        }
    }

    @Override
    public String getSubjectFromToken(String token){
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("codeforces-recsys")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalid or expired");
        }
    }

    private Instant expirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}