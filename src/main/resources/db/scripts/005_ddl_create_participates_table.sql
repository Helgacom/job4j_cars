CREATE TABLE participates(
   id bigserial PRIMARY KEY,
   user_id bigint not null REFERENCES auto_user(id),
   post_id bigint not null REFERENCES auto_post(id),
   UNIQUE (user_id, post_id)
);
