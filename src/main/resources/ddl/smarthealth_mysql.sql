CREATE DATABASE smarthealth;
USE smarthealth;

CREATE TABLE account (
  id                    bigint(20)       NOT NULL AUTO_INCREMENT,
  username              VARCHAR(255)     NOT NULL,
  password              VARCHAR(255)     NOT NULL,
  PRIMARY KEY (id),
  INDEX account_username_idx (username)
);

-- password: admin
INSERT INTO account (username, password) VALUES ('admin', '$2a$10$ssFkj/wANdJSj3BKUNGsm.MR.AFmhuEKj0TiVicWQMSzbmBnIPm5y');
-- password: alan
INSERT INTO account (username, password) VALUES ('alan', '$2a$10$y9jEKOs2Zb1sdlnQ6l0.qOZIh99WioeWd4/e6HHBb.TLANbG7FSS2');
-- password: mike
INSERT INTO account (username, password) VALUES ('mike', '$2a$10$kHtBEsHddhW4Vlxaiomrzu1b4NeWJNL20NjBxH1I4aGH52gZZd/8q');
-- password: sally
INSERT INTO account (username, password) VALUES ('sally', '$2a$10$gPuqv64XvjQFqkwsjucVUOXpMtpbAfEHhHQGmzBJZcdWKb71wRMIy');
