CREATE TABLE "users"
(
    "id"            SERIAL PRIMARY KEY,
    "username"      VARCHAR(255) NOT NULL UNIQUE,
    "first_name"     VARCHAR(255) NOT NULL,
    "last_name"      VARCHAR(255) NOT NULL,
    "password"      VARCHAR(255) NOT NULL,
    "created_at"    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "messages"
(
    "id"            SERIAL PRIMARY KEY,
    "sender_id"     INTEGER NOT NULL,
    "receiver_id"   INTEGER NOT NULL,
    "content"       TEXT NOT NULL,
    "is_read"       BOOLEAN NOT NULL DEFAULT FALSE,
    "created_at"    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY ("sender_id") REFERENCES "users" ("id"),
    FOREIGN KEY ("receiver_id") REFERENCES "users" ("id")
);
