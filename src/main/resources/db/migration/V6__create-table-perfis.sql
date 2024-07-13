create table perfis (
    id bigint not null auto_increment,
    nome varchar(100) not null,

    primary key(id)
);

CREATE TABLE perfil_usuarios (
    perfil_id bigint not null,
    usuario_id bigint not null,
    primary key (perfil_id, usuario_id),
    foreign key (perfil_id) references perfis(id) on delete cascade,
    foreign key (usuario_id) references usuarios(id) on delete cascade
);