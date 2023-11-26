CREATE TABLE IF NOT EXISTS documents
(
    id         bigserial                NOT NULL PRIMARY KEY,
    number     bigint    UNIQUE         NOT NULL,
    hash_sum   bigint                   NOT NULL,
    note       text,
    created_at timestamp                NOT NULL DEFAULT now(),
    updated_at timestamp                NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS positions
(
    id       bigserial                                              NOT NULL PRIMARY KEY,
    doc_num  bigint REFERENCES documents (number) ON DELETE CASCADE NOT NULL,
    pos_num  bigint                                                 NOT NULL,
    pos_name text                                                   NOT NULL,
    hash_sum bigint                                                 NOT NULL
);

CREATE TABLE IF NOT EXISTS errors
(
    id          bigserial                                              NOT NULL PRIMARY KEY,
    doc_num     bigint REFERENCES documents (number) ON DELETE CASCADE NOT NULL,
    err_msg     text,
    created_at  timestamp                                              NOT NULL DEFAULT now()
);