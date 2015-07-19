package com.springapp.jpa;

import com.springapp.configuration.persistence.PersistenceConfiguration;
import com.springapp.configuration.root.ApplicationConfig;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * This class contains tests for testing bd connection.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfiguration.class , ApplicationConfig.class}, loader = AnnotationConfigContextLoader.class)
public class TestingJPAConnection {

    private static final String SOME_EXERCISE_NAME = "some_exercise_name";
    private static final String SOME_EXERCISE_DESCRIPTION = "some_exercise_description";

    @Autowired
    private ExerciseRepository sut;

    @Before
    public final void before() {

    }

    @Test
    public final void shouldInsertOneExercise(){
        final Exercise exercise = new Exercise();
        exercise.setName(SOME_EXERCISE_NAME);
        exercise.setDescription(SOME_EXERCISE_DESCRIPTION);
        sut.save(exercise);
    }

    @Test
    public final void shouldFindOneExercise(){

        final List<Exercise> result = sut.findAll();

        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result contains more element than expected.", 1, CollectionUtils.size(result));
        Assert.assertEquals("Description is different than expected", SOME_EXERCISE_DESCRIPTION, result.get(0).getDescription());
        Assert.assertEquals("Name is different than expected", SOME_EXERCISE_NAME, result.get(0).getName());
    }

    @Test
    public final void shouldCountOneExercise(){

        //sut.findOne()
    }

    @Test
    public final void shouldDeleteOneExercise(){

    }



}
