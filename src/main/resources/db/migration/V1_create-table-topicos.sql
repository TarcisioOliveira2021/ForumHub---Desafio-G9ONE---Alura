create table topicos (

    id bigint not null auto_increment,
    titulo varchar(150) not null unique,
    mensagem varchar(250) not null unique,
    data_criacao date not,
    estatus varchar(100) not null,
    autor varchar(150) not null,
    curso varchar(100) not null,

    primary key(id)
);