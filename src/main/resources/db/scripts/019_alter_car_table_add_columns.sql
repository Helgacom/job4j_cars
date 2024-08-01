ALTER TABLE car DROP COLUMN name;

ALTER TABLE car ADD category_id BIGINT DEFAULT 1 NOT NULL REFERENCES categories(id);

UPDATE car SET category_id = 1 WHERE id = 1;
UPDATE car SET category_id = 1 WHERE id = 2;
UPDATE car SET category_id = 1 WHERE id = 3;
UPDATE car SET category_id = 1 WHERE id = 4;
UPDATE car SET category_id = 1 WHERE id = 5;
UPDATE car SET category_id = 1 WHERE id = 6;
UPDATE car SET category_id = 1 WHERE id = 7;
UPDATE car SET category_id = 1 WHERE id = 8;

ALTER TABLE car ADD model_id BIGINT DEFAULT 1 NOT NULL REFERENCES model(id);

UPDATE car SET model_id = 1 WHERE id = 1;
UPDATE car SET model_id = 2 WHERE id = 2;
UPDATE car SET model_id = 3 WHERE id = 3;
UPDATE car SET model_id = 4 WHERE id = 4;
UPDATE car SET model_id = 5 WHERE id = 5;
UPDATE car SET model_id = 6 WHERE id = 6;
UPDATE car SET model_id = 7 WHERE id = 7;
UPDATE car SET model_id = 8 WHERE id = 8;

ALTER TABLE car ADD type_id BIGINT DEFAULT 1 NOT NULL REFERENCES types(id);

UPDATE car SET type_id = 3 WHERE id = 1;
UPDATE car SET type_id = 2 WHERE id = 2;
UPDATE car SET type_id = 1 WHERE id = 3;
UPDATE car SET type_id = 1 WHERE id = 4;
UPDATE car SET type_id = 1 WHERE id = 5;
UPDATE car SET type_id = 4 WHERE id = 6;
UPDATE car SET type_id = 4 WHERE id = 7;
UPDATE car SET type_id = 4 WHERE id = 8;