
CREATE TABLE exercise (
  id          INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(45) NULL,
  description VARCHAR(45) NULL,
  PRIMARY KEY (id)
);


CREATE TABLE user (
  id       INT         NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NULL,
  PRIMARY KEY (id)
);