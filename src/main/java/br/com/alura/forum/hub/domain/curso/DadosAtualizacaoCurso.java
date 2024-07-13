package br.com.alura.forum.hub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoCurso(
        String nome,
        Categoria categoria
) {
}
