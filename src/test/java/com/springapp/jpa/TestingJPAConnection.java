package com.springapp.jpa;

import com.springapp.configuration.persistence.PersistenceConfiguration;
import com.springapp.configuration.root.ApplicationConfig;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * This class contains tests for testing bd connection.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = { PersistenceConfiguration.class , ApplicationConfig.class}, loader = AnnotationConfigContextLoader.class)
public class TestingJPAConnection {

    private static final String SOME_EXERCISE_NAME = "some_exercise_name";
    private static final String SOME_EXERCISE_DESCRIPTION = "some_ex_description";

    @Autowired
    private ExerciseRepository sut;

    @Test
    public final void shouldInsertOneExercise(){
        final Exercise exercise = new Exercise();
        exercise.setName(SOME_EXERCISE_NAME);
        exercise.setDescription(SOME_EXERCISE_DESCRIPTION);
        sut.save(exercise);
    }

}
