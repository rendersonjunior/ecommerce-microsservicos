create table products.PRODUTO (
    id bigserial primary key,
    product_identifier varchar not null,
    nome varchar(100) not null,
    descricao varchar not null,
    preco numeric not null,
    category_id bigint references products.categoria(id),
    data_cadastro timestamp not null,
    data_atualizacao timestamp
);