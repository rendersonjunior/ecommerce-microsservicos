CREATE SCHEMA IF NOT EXISTS products;

create table products.CATEGORIA (
   id bigserial primary key,
   nome varchar(100) not null
);

