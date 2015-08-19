INSERT INTO exercise (name, description) VALUES ('test1', 'description test 1');
INSERT INTO exercise (name, description) VALUES ('test2', 'description test 2');
INSERT INTO exercise (name, description) VALUES ('test3', 'description test 3');


INSERT INTO user (username) VALUES ('user1');
INSERT INTO user (username) VALUES ('user2');
INSERT INTO user (username) VALUES ('user3');

INSERT INTO user_exercise (user_id,exercise_id, created_date ) VALUES (1,1, '2012-07-24' );
INSERT INTO user_exercise (user_id,exercise_id,created_date ) VALUES (1,2,'2012-07-24' );
INSERT INTO user_exercise (user_id,exercise_id,created_date ) VALUES (3,2,'2012-07-24' );
