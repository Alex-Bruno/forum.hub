package br.com.alura.forum.hub.domain.topico;

import br.com.alura.forum.hub.domain.curso.Curso;
import br.com.alura.forum.hub.domain.curso.CursoRepository;
import br.com.alura.forum.hub.domain.exception.ValidacaoException;
import br.com.alura.forum.hub.domain.topico.validacoes.IValidadorTopicos;
import br.com.alura.forum.hub.domain.topico.validacoes.ValidadorUsuarioLogado;
import br.com.alura.forum.hub.domain.usuario.Usuario;
import br.com.alura.forum.hub.domain.usuario.UsuarioRepository;
import br.com.alura.forum.hub.domain.usuario.UsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private List<IValidadorTopicos> validadores;

    public Topico cadastrar(DadosCadastroTopico dados) {
        var topico = new Topico(dados);
        topico.setAutor(getUsuario());
        topico.setCurso(getCurso(dados.curso()));
        repository.save(topico);
        return topico;
    }

    public Topico atualizar(Long id, DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(id);

        validadores.forEach(v -> v.validar(topico));

        topico.atualizarInformacoes(dados);
        if (dados.curso() != null) {
            topico.setCurso(getCurso(dados.curso()));
        }

        return topico;
    }

    private Usuario getUsuario() {
        var usuario = UsuarioUtil.getCurrentUser();
        if (usuario == null) {
            throw new ValidacaoException("Usuário não cadastrado!");
        }
        return (Usuario) usuario;
    }

    private Curso getCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ValidacaoException("Id do curso informado não existe!");
        }
        return cursoRepository.getReferenceById(id);
    }
}
