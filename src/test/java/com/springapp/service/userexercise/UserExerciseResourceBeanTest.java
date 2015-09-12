package com.springapp.service.userexercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.SimpleUserExercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.model.UserExercise;
import com.springapp.jpa.model.UserExerciseId;
import com.springapp.jpa.repository.UserExerciseRepository;
import com.springapp.utils.exercise.ExerciseStubFactory;
import com.springapp.utils.user.UserStubFactory;
import com.springapp.utils.userexercise.UserExerciseStubFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * This class contains tests for verify that the User Exercise Resource works correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserExerciseResourceBeanTest {

    private static final Integer SOME_USER_ID = 123;
    private static final Integer SOME_EXERCISE_ID = 123;

    @Mock
    private UserExerciseRepository userExerciseRepository;

    @InjectMocks
    private UserExerciseResourceBean sut;

    @Before
    public void init() {
    }

    @After
    public void after() {
        verifyNoMoreInteractions(userExerciseRepository);
    }

    @Test
    public void shouldSaveExercise() {

        final User user = UserStubFactory.createStubbedUser(SOME_USER_ID);
        final Exercise exercise = ExerciseStubFactory.createStubExercise(SOME_EXERCISE_ID);
        final UserExercise userExercise = UserExerciseStubFactory.createStubbedUserExercise(user, exercise);

        // Mock dependencies
        when(userExerciseRepository.save(isA(UserExercise.class)))
                .thenReturn(userExercise);

        // Run fixture
        final UserExercise result = sut.save(userExercise);

        // Verify result
        assertNotNull("Result is null", result);
        assertEquals("Result is different than expected", userExercise, result);

        // Verify mock
        verify(userExerciseRepository, times(1)).save(userExercise);
    }

    @Test
    public void shouldFindUserExercise() {

        final User user = UserStubFactory.createStubbedUser(SOME_USER_ID);
        final Exercise exercise = ExerciseStubFactory.createStubExercise(SOME_EXERCISE_ID);
        final UserExercise userExercise = UserExerciseStubFactory.createStubbedUserExercise(user, exercise);
        final UserExerciseId userExerciseId = UserExerciseStubFactory.createStubbedUSerExerciseId(user, exercise);

        // Mock dependencies
        when(userExerciseRepository.findOne(isA(UserExerciseId.class)))
                .thenReturn(Optional.of(userExercise));

        final UserExercise result = sut.findUserExercise(userExerciseId);

        // Verify result
        assertNotNull("Result is null", result);
        assertEquals("Result is different than expected", userExercise, result);

        //Verify mocks
        verify(userExerciseRepository, times(1)).findOne(userExerciseId);
    }

    @Test
    public void shouldNotFindUserExercise() {

        final User user = UserStubFactory.createStubbedUser(SOME_USER_ID);
        final Exercise exercise = ExerciseStubFactory.createStubExercise(SOME_EXERCISE_ID);
        final UserExerciseId userExerciseId = UserExerciseStubFactory.createStubbedUSerExerciseId(user, exercise);

        when(userExerciseRepository.findOne(isA(UserExerciseId.class)))
                .thenReturn(Optional.empty());

        try {
            sut.findUserExercise(userExerciseId);
        } catch (final EntityNotFoundException ex) {
            //Verify mocks
            verify(userExerciseRepository, times(1)).findOne(userExerciseId);
        }
    }

    @Test
    public void shouldDeleteUserExercise() {

        final User user = UserStubFactory.createStubbedUser(SOME_USER_ID);
        final Exercise exercise = ExerciseStubFactory.createStubExercise(SOME_EXERCISE_ID);
        final UserExerciseId userExerciseId = UserExerciseStubFactory.createStubbedUSerExerciseId(user, exercise);

        sut.deleteUserExercise(exercise, user);

        verify(userExerciseRepository, times(1)).delete(userExerciseId);
    }

    @Test
    public void shouldGetUsersExercises() {

        final Collection<UserExercise> userExercises = UserExerciseStubFactory.createStubbedUserExercise(3);

        when(userExerciseRepository.findAll())
                .thenReturn(userExercises);

        final Collection<SimpleUserExercise> result = sut.getUsersExercises();

        assertNotNull("Result is null", result);

        verify(userExerciseRepository, times(1)).findAll();
    }
}
