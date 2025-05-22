--liquibase formatted sql
--changeset nikolaev:create_calc_events_table
CREATE TABLE calc_events
(
    user_id    int,
    first      int,
    second     int,
    result     int,
    create_date timestamp,
    type       varchar(5)
);