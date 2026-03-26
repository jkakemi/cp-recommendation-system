package br.com.ufu.recsys.identityaccess.adapters.in.web.dto;

public record LoginRequest(
        String email,
        String password
) {
}
