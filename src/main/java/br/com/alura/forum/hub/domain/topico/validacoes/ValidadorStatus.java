package br.com.alura.forum.hub.domain.topico.validacoes;

import br.com.alura.forum.hub.domain.exception.ValidacaoException;
import br.com.alura.forum.hub.domain.topico.Topico;
import br.com.alura.forum.hub.domain.topico.TopicoStatus;
import br.com.alura.forum.hub.domain.usuario.UsuarioRepository;
import br.com.alura.forum.hub.domain.usuario.UsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorStatus implements IValidadorTopicos{
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(Topico topico) {
        if (topico.getStatus() == TopicoStatus.DELETADO) {
            throw new ValidacaoException("Id do tópico informado não existe!");
        }
    }
}
