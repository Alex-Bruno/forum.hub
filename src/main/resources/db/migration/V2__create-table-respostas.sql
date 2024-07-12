create table respostas (
    id bigint not null auto_increment,
    mensagem text not null,
    topico_id bigint not null,
    dataCriacao date not null,
    autor_id bigint not null,
    solucao text not null,

    primary key(id),
    constraint fk_respostas_topico_id foreign key(topico_id) references topicos(id)
);