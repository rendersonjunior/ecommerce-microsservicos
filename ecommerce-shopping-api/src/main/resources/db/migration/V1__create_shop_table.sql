create schema if not exists shopping;

create table shopping.shop (
    id bigserial primary key,
    user_identifier varchar(100),
    date timestamp not null,
    total numeric not null
);

create table shopping.item (
    shop_id bigserial references shopping.shop(id),
    product_identifier varchar(100) not null,
    preco numeric not null
);
