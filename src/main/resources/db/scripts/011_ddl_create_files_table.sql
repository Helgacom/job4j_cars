create table files(
    id   bigserial primary key,
    name varchar not null,
    path varchar not null unique
);