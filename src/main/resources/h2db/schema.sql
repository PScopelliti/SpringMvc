CREATE TABLE exercise (
  exercise_id INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(45) NULL,
  description VARCHAR(45) NULL,
  PRIMARY KEY (exercise_id)
);

CREATE TABLE user (
  user_id  INT         NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE user_exercise (
  exercise_id  INT  NOT NULL,
  user_id      INT  NOT NULL,
  created_date DATE NOT NULL,
  PRIMARY KEY (exercise_id, user_id),
  CONSTRAINT fk_user_exercise_exercise
  FOREIGN KEY (exercise_id)
  REFERENCES exercise (exercise_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_user_exercise_user
  FOREIGN KEY (user_id)
  REFERENCES user (user_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);
