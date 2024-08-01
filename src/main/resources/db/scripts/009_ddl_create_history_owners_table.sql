create table history_owners(
    id       bigserial primary key,
    owner_id bigint not null references owners (id),
    car_id   bigint not null references car (id),
    startAt timestamp,
    endAt timestamp,
    UNIQUE (owner_id, car_id)
);