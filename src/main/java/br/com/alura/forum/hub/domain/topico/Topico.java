package br.com.alura.forum.hub.domain.topico;

import br.com.alura.forum.hub.domain.curso.Curso;
import br.com.alura.forum.hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private TopicoStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();

        this.dataCriacao = LocalDateTime.now();
        this.status = TopicoStatus.ABERTO;
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
    }

    public void excluir() {
        this.status = TopicoStatus.DELETADO;
    }
}
