CREATE TABLE car(
    id bigserial primary key,
    name varchar not null,
    engine_id bigint not null unique references engine(id)
);