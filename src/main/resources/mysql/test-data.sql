CREATE SCHEMA `spring`;


CREATE TABLE `spring`.`exercise` (
  `exercise_id` INT         NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`exercise_id`)
);


CREATE TABLE `spring`.`user` (
  `user_id`  INT         NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `spring`.`user_exercise` (
  `user_id`     INT NOT NULL,
  `exercise_id` INT NOT NULL,
  PRIMARY KEY (`exercise_id`, `user_id`),
  INDEX `FK_EXERCISE` (`exercise_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_EXERCISE` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`exercise_id`)
);