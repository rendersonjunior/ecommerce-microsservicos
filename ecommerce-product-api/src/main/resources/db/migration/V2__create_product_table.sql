create sequence products.sq_product
start with 1
increment by 1;

create table products.product (
    id bigint primary key,
    product_identifier varchar not null,
    nome varchar(100) not null,
    descricao varchar not null,
    preco numeric not null,
    category_id bigint references products.category(id),
    data_cadastro timestamp not null,
    data_atualizacao timestamp
);