CREATE SCHEMA `spring`;


CREATE TABLE `spring`.`execise` (
  `id`          INT         NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `spring`.`user` (
  `id`       INT         NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);