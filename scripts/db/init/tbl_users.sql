CREATE DATABASE mydb;

create table mydb.users
(
    id        varchar(100) not null
        primary key,
    name      varchar(100) null,
    password  varchar(100) null,
    level     int      not null,
    login     int          not null,
    recommend int          not null,
    email     varchar(100) not null
);

