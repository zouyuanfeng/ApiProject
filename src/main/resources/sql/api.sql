CREATE TABLE API (
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  method      VARCHAR(30)     NOT NULL,
  response    VARCHAR(1000)   NOT NULL,
  groupname   VARCHAR(30)     NOT NULL,
  description VARCHAR(1000)
);


SELECT *
FROM API;
