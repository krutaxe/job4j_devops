--liquibase formatted sql
--changeset nikolaev:alter_users_table
ALTER TABLE users
    ADD COLUMN first_arg DOUBLE PRECISION,
    ADD COLUMN second_arg DOUBLE PRECISION,
    ADD COLUMN result DOUBLE PRECISION;