CREATE TABLE users
(
    id       serial
        CONSTRAINT users_pk PRIMARY KEY,
    login    varchar NOT NULL,
    password varchar,
    is_admin bool DEFAULT FALSE
);

INSERT INTO users(login, password, is_admin)
VALUES ('admin', 'admin', true),
       ('superAdmin', 'admin', true),
       ('user', 'user', false),
       ('you', 'you', false);