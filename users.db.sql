BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "user" (
                                          "name" TEXT,
                                          "surname" TEXT,
                                          "email" TEXT,
                                          "username" TEXT,
                                          "password" TEXT
);
COMMIT;