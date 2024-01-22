CREATE TABLE "public".url (
    "id"           BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (start 1),
    short_url      VARCHAR(24) NOT NULL,
    created_at     TIMESTAMP DEFAULT NOW(),
    retention      INTEGER DEFAULT 2,
    visited        INTEGER DEFAULT 0,
    long_url       VARCHAR(2050) NOT NULL
);

CREATE TABLE "public".account_url (
    account_id     BIGINT,
    url_id         BIGINT
);

CREATE TABLE "public".account (
    "id"           BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (start 1),
    name           VARCHAR(24) NOT NULL,
    created_at     TIMESTAMP DEFAULT NOW()
);