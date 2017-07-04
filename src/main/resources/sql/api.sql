CREATE TABLE API (
  id          INT PRIMARY KEY NOT NULL,
  method      VARCHAR(30)     NOT NULL UNIQUE,
  response    VARCHAR(1000)   NOT NULL,
  description VARCHAR(1000)
);

# 修改id为自增
ALTER TABLE api.API
  MODIFY id INT(11) NOT NULL AUTO_INCREMENT;

SELECT *
FROM API;

ALTER TABLE api.API ADD groupname VARCHAR(30) NOT NULL;

INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test83', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test13', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test32', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test33', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test34', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test35', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test36', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test73', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test38', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test39', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test30', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test43', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test44', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test45', 'safdsfsda', 'dfsdfs');
INSERT INTO API (.API.method, .API.response, .API.description) VALUES ('test56', 'safdsfsda', 'dfsdfs');

UPDATE API SET groupname='test';

SELECT DISTINCT API.groupname FROM API;