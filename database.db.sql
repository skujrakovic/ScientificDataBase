BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "user" (
    "name" TEXT,
    "surname" TEXT,
    "email" TEXT,
    "username" TEXT,
    "password" TEXT,
    PRIMARY KEY ("username")
);
CREATE TABLE IF NOT EXISTS "scientific_paper" (
    "sid" INTEGER,
    "title" TEXT,
    "year" INTEGER,
    "genre" TEXT,
    "type" TEXT,
    "link" TEXT,
    "summary" TEXT,
    PRIMARY KEY("sid")
);
CREATE TABLE IF NOT EXISTS "author" (
    "aid" INTEGER,
    "full_name" TEXT,
    "fk_sid" INTEGER,
    PRIMARY KEY ("aid"),
    FOREIGN KEY ("fk_sid") REFERENCES scientific_paper ("sid")
);
COMMIT;