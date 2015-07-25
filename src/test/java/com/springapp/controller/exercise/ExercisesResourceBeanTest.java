package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import com.springapp.utils.exercise.ExerciseStubFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class contains methods for verify that the Exercises controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExercisesResourceBeanTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExercisesResourceBean sut;

    @Test
    public void testExercisesPage() throws Exception {

        final List<Exercise> expectedExercises = ExerciseStubFactory.createStubExercisesList(5);

        when(exerciseRepository.findAll()).thenReturn(expectedExercises);

        // Run fixture
        final List<Exercise> result = sut.getExercises();

        Assert.assertNotNull("Result is null.", result);
        Assert.assertEquals("Size is different than expected.", expectedExercises.size(), result.size());
        Assert.assertEquals("List in different than expected.", expectedExercises.get(0), result.get(0));

        // Verify mock
        verify(exerciseRepository, times(1)).findAll();
    }
}
