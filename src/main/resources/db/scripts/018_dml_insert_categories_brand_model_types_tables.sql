insert into categories (name)
values ('passenger''s'),
       ('cargo'),
       ('commercial'),
       ('bus');

insert into brand (name)
values ('Porsche'),
       ('Audi'),
       ('Mazda'),
       ('BMW');

insert into model (name, brand_id)
values ('911', 1),
       ('A1', 2),
       ('A2', 2),
       ('A3', 2),
       ('A6', 2),
       ('CX3', 3),
       ('CX5', 3),
       ('X8', 4);

insert into types (name)
values ('sedan'),
       ('hatchback'),
       ('coupe'),
       ('suv');