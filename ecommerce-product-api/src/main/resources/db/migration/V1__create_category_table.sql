create schema if not exists products;

create table products.category (
   id int auto_increment primary key,
   nome varchar(100) not null
);

