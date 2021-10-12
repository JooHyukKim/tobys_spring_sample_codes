CREATE
DATABASE mydb;

CREATE TABLE mydb.users
(
    id        VARCHAR(100) PRIMARY KEY,
    name      VARCHAR(100),
    password  VARCHAR(100),
    level     TINYINT NOT NULL,
    login     INT     NOT NULL,
    recommend INT     NOT NULL

);
