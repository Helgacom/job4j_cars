create table auto_post (
    id bigserial primary key,
    description varchar not null,
    created timestamp,
    auto_user_id bigint references auto_user(id)
);