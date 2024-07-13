package br.com.alura.forum.hub.controller;

import br.com.alura.forum.hub.domain.curso.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> listar(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemCurso::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCurso> detalhar(@PathVariable Long id) {
        var curso = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> cadastrar(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(dados);
        repository.save(curso);

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoCurso dados) {
        var curso = repository.getReferenceById(id);
        curso.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }
}
