create schema if not exists shopping;

create table shopping.shop (
    id int auto_increment primary key,
    user_identifier varchar(100),
    date timestamp not null,
    total numeric(20,2) not null
);

create table shopping.item (
    shop_id int auto_increment references shopping.shop(id),
    product_identifier varchar(100) not null,
    preco numeric(20,2) not null
);
