create table model (
    id       bigserial primary key,
    name     TEXT not null,
    brand_id BIGINT  NOT NULL REFERENCES brand (id)
);