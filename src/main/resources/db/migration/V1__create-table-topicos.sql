create table topicos (
     id bigint not null auto_increment,
     titulo varchar(100) not null,
     mensagem text not null,
     data_criacao date not null,
     status varchar(50) not null,
     autor_id bigint not null,
     curso_id bigint not null,

     primary key(id)
);