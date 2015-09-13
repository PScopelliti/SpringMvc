CREATE SCHEMA `spring`;


CREATE TABLE `spring`.`exercise` (
  `exercise_id` INT         NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`exercise_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


CREATE TABLE `spring`.`user` (
  `user_id`  INT         NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE `spring`.`user_exercise` (
  `user_id`      INT  NOT NULL,
  `exercise_id`  INT  NOT NULL,
  `created_date` DATE NOT NULL,
  PRIMARY KEY (`user_id`,`exercise_id`),
  INDEX `fk_user_exercise_user_idx` (`user_id` ASC),
  INDEX `fk_user_exercise_exercise_idx` (`exercise_id` ASC),
  CONSTRAINT `FK_USER`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_EXERCISE`
  FOREIGN KEY (`exercise_id`)
  REFERENCES `exercise` (`exercise_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;