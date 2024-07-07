----------------------------------------
-- SCRIPT START (DB_USER)
----------------------------------------
----------------------------------------
-- DB, SCHEMA, SCHEMA_OWNER
----------------------------------------

CREATE USER owner WITH ENCRYPTED PASSWORD 'owner_pwd';

GRANT ALL PRIVILEGES ON DATABASE postgres TO owner;

CREATE SCHEMA discounts AUTHORIZATION owner;