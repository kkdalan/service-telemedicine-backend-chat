CREATE DATABASE smarthealth;
USE smarthealth;

CREATE TABLE account (
  username              VARCHAR(255)     NOT NULL,
  password              VARCHAR(255)     NOT NULL,
  PRIMARY KEY (username),
  INDEX account_username_idx (username)
);

-- password: 0000
INSERT INTO account (username, password) VALUES ('admin', '$2a$10$82y7KDKTXRs09Age7Jq1xO3Sf/jb.A0Lk0LvEo0YLHZQL094XEg2u');
-- password: imalan
INSERT INTO account (username, password) VALUES ('alanhuang', '$2a$10$NhZOu23f3uS/Wrc1u.ZFOe.nCwV3Y9JbuhKK4l6gYsSTLJBN5.N7S');
-- password: mike
INSERT INTO account (username, password) VALUES ('mike', '$2a$10$kHtBEsHddhW4Vlxaiomrzu1b4NeWJNL20NjBxH1I4aGH52gZZd/8q');

