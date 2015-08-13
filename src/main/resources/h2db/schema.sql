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
  user_id     INT NOT NULL,
  exercise_id INT NOT NULL,
  PRIMARY KEY (user_id, exercise_id),
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(user_id),
  CONSTRAINT fk_exercise FOREIGN KEY (exercise_id) REFERENCES exercise(exercise_id)
)