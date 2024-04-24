CREATE TABLE IF NOT EXISTS books
(
    id       BIGSERIAL PRIMARY KEY,
    name     character varying(255) NOT NULL,
    language character varying(255) NOT NULL,
    category character varying(255) NOT NULL
);