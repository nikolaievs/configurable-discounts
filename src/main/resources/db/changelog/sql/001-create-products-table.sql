CREATE TABLE IF NOT EXISTS product
(
    id    uuid PRIMARY KEY NOT NULL,
    name  VARCHAR(50)      NOT NULL,
    price NUMERIC(14, 2)   NOT NULL
);
