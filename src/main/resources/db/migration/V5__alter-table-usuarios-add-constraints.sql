ALTER TABLE topicos add constraint fk_topicos_autor_id foreign key(autor_id) references usuarios(id);
ALTER TABLE topicos add constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id);
ALTER TABLE respostas add constraint fk_respostas_autor_id foreign key(autor_id) references usuarios(id);