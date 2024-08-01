create table auto_user(
    id bigserial primary key,
    login varchar not null unique,
    password varchar not null
);