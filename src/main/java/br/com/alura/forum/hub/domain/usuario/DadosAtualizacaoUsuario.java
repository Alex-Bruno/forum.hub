package br.com.alura.forum.hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUsuario(
        String nome,
        @Pattern(regexp = "^.{6,}$")
        String senha
) {
}
