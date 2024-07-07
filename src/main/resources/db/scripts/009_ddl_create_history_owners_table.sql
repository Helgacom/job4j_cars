create table history_owners(
    id       serial primary key,
    owner_id int not null references owners (id),
    car_id   int not null references car (id),
    UNIQUE (owner_id, car_id)
);