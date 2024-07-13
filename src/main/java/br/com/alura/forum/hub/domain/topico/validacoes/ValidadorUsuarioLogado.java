package br.com.alura.forum.hub.domain.topico.validacoes;

import br.com.alura.forum.hub.domain.exception.ValidacaoException;
import br.com.alura.forum.hub.domain.topico.Topico;
import br.com.alura.forum.hub.domain.usuario.UsuarioRepository;
import br.com.alura.forum.hub.domain.usuario.UsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioLogado implements IValidadorTopicos{
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(Topico topico) {
        var usuario = UsuarioUtil.getCurrentUser();

        if (usuario == null || !usuario.getUsername().equalsIgnoreCase(topico.getAutor().getUsername())) {
            throw new ValidacaoException("Você não possuí autorização para alterar este tópico.");
        }
    }
}
