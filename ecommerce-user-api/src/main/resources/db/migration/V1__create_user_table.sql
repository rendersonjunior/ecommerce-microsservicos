create schema if not exists users;

create sequence users.sq_users
start with 1
increment by 1;

create table users.users(
      id bigint primary key,
      nome varchar(100) not null,
      cpf varchar(11) not null,
      endereco varchar(100) not null,
      email varchar(100) not null,
      telefone varchar(100) not null,
      data_cadastro timestamp not null
);