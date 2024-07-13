package br.com.alura.forum.hub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public Usuario cadastrar(DadosCadastroUsuario dados) {
        var usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        repository.save(usuario);
        return usuario;
    }

    public Usuario atualizar(Long id, DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(id);

        if (dados.nome() != null) {
            usuario.atualizarNome(dados.nome());
        }

        if (dados.senha() != null) {
            usuario.atualizarSenha(passwordEncoder.encode(dados.senha()));
        }

        return usuario;
    }
}
