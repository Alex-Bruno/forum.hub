package br.com.alura.forum.hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank
        String email,
        @NotBlank
        String senha
) {
}
