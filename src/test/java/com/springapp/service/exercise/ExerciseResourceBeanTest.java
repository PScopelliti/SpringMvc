package com.springapp.service.exercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import com.springapp.utils.exercise.ExerciseStubFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * This class contains tests for verify that the Exercise Resource works correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExerciseResourceBeanTest {

    private static final Integer EXERCISE_ID = 123;
    private static final String SOME_NEW_DESCRIPTION = "some_new_description";
    private static final String SOME_NEW_NAME = "some_new_name";

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseResourceBean sut;

    @Before
    public void init() {

    }

    @After
    public void after() {
        verifyNoMoreInteractions(exerciseRepository);
    }

    /**
     * Test if the controller returns the expected result when is called
     * for saving exercises.
     */
    @Test
    public void shouldAddNewExercise() {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(EXERCISE_ID);

        when(exerciseRepository.save(any(Exercise.class)))
                .thenReturn(exercise);

        final Exercise result = sut.save(exercise);

        //Verify result
        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result body is different than expected.", exercise, result);

        // Verify mock
        verify(exerciseRepository, times(1)).save(exercise);
    }

    /**
     * This test verify that exercise resource bean get all expected exercises.
     *
     * @throws Exception
     */
    @Test
    public void testExercisesPage() throws Exception {

        final List<Exercise> expectedExercises = new ArrayList<>(ExerciseStubFactory.createStubExercisesList(5));

        when(exerciseRepository.findAll()).thenReturn(expectedExercises);

        // Run fixture
        final List<Exercise> result = new ArrayList<>(sut.getExercises());

        Assert.assertNotNull("Result is null.", result);
        Assert.assertEquals("Size is different than expected.", expectedExercises.size(), result.size());
        Assert.assertEquals("List in different than expected.", expectedExercises.get(0), result.get(0));

        // Verify mock
        verify(exerciseRepository, times(1)).findAll();
    }

    /**
     * Verify if the controller return the correct response when
     * is called with an existing exercise id.
     */
    @Test
    public void shouldReturnExerciseDetails() {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(EXERCISE_ID);

        when(exerciseRepository.findOne(anyInt()))
                .thenReturn(Optional.of(exercise));

        final Exercise result = sut.findExercise(EXERCISE_ID);

        // Verify response
        Assert.assertNotEquals("Result is null", result);
        Assert.assertEquals("Result is different than expected", exercise, result);

        // Verify mocks
        verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
    }


    /**
     * This test verify that exercise is deleted correctly.
     */
    @Test
    public void shouldRemoveOneExercise() {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(EXERCISE_ID);

        when(exerciseRepository.findOne(anyInt()))
                .thenReturn(Optional.of(exercise));

        sut.deleteExerciseById(EXERCISE_ID);

        verify(exerciseRepository, times(1)).delete(EXERCISE_ID);
        verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
    }


    /**
     * This test verify that the exercise is updated correctly.
     */
    @Test
    public void shouldBeanUpdateExercise() {

        final Exercise originalEx = ExerciseStubFactory.createStubExercise(EXERCISE_ID);
        final Exercise updatedEx = ExerciseStubFactory
                .createStubExerciseWithCustomFields(EXERCISE_ID,
                        SOME_NEW_DESCRIPTION,
                        SOME_NEW_NAME);

        when(exerciseRepository.findOne(anyInt()))
                .thenReturn(Optional.of(originalEx));

        when(exerciseRepository.save(any(Exercise.class)))
                .thenReturn(updatedEx);

        final Exercise result = sut.updateExercise(updatedEx, EXERCISE_ID);

        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result is different than expected", result, updatedEx);

        verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
        verify(exerciseRepository, times(1)).save(updatedEx);

    }

    /**
     * This test verify that if the Exercise with specified Id doesn't exist
     * the returned value is null.
     */
    @Test
    public void shouldReturnNullBecauseExerciseIdDoesNotExist() {

        final Exercise updatedEx = ExerciseStubFactory
                .createStubExerciseWithCustomFields(EXERCISE_ID,
                        SOME_NEW_DESCRIPTION,
                        SOME_NEW_NAME);

        when(exerciseRepository.findOne(anyInt()))
                .thenReturn(Optional.empty());

        try {
            sut.updateExercise(updatedEx, EXERCISE_ID);
        } catch (EntityNotFoundException ex) {
            verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
        }
    }


}
