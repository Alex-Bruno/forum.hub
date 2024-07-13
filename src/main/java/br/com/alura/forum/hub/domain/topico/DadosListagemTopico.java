package br.com.alura.forum.hub.domain.topico;

import br.com.alura.forum.hub.domain.curso.Curso;
import br.com.alura.forum.hub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosListagemTopico(
    Long id,
    String titulo,
    String mensagem,
    LocalDateTime dataCriacao,
    TopicoStatus status,
    Usuario autor,
    Curso curso
) {
    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
