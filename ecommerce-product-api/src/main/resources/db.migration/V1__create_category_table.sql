create schema if not exists products;

create table products.categoria (
    id bigserial primary key,
    nome varchar(100) not null
);