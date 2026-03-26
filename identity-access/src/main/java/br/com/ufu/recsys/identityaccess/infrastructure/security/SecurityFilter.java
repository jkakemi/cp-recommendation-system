package br.com.ufu.recsys.identityaccess.infrastructure.security;

import br.com.ufu.recsys.identityaccess.domain.ports.out.JwtPort;
import br.com.ufu.recsys.identityaccess.domain.ports.out.UserRepositoryPort;
import br.com.ufu.recsys.identityaccess.domain.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtPort jwtPort;
    private final UserRepositoryPort userRepositoryPort;

    public SecurityFilter(JwtPort jwtPort, UserRepositoryPort userRepositoryPort) {
        this.jwtPort = jwtPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            try {
                var email = jwtPort.getSubjectFromToken(token);

                var userOptional = userRepositoryPort.findByEmail(email);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();

                    var authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (RuntimeException ex) {
                System.out.println("Token inválido ou expirado: " + ex.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}