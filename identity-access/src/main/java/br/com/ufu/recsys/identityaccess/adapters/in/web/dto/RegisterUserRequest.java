package br.com.ufu.recsys.identityaccess.adapters.in.web.dto;

public record RegisterUserRequest(
        String email,
        String password
) {
}
