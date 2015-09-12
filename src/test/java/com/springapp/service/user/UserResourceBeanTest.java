package com.springapp.service.user;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.repository.UserRepository;
import com.springapp.utils.exercise.ExerciseStubFactory;
import com.springapp.utils.user.UserStubFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * This class contains tests for verify that the User Resource works correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserResourceBeanTest {

    private static final Integer USER_ID = 123;
    private static final String SOME_NEW_USERNAME = "some_new_username";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserResourceBean sut;

    @Before
    public void init() {
    }

    @After
    public void after() {
        verifyNoMoreInteractions(userRepository);
    }

    /**
     * This test verify that user is deleted correctly.
     */
    @Test
    public void shouldRemoveOneUser() {

        final User user = UserStubFactory.createStubbedUser(USER_ID);

        when(userRepository.findOne(anyInt()))
                .thenReturn(Optional.of(user));

        sut.deleteUserById(USER_ID);

        verify(userRepository, times(1)).delete(USER_ID);
        verify(userRepository, times(1)).findOne(USER_ID);
    }

    /**
     * Test if the controller returns the expected result when is called
     * for saving users.
     */
    @Test
    public void shouldAddNewUser() {

        final User user = UserStubFactory.createStubbedUser(USER_ID);

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        final User result = sut.save(user);

        //Verify result
        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result body is different than expected.", user, result);

        // Verify mock
        verify(userRepository, times(1)).save(user);
    }


    /**
     * Verify if the controller return the correct response when
     * is called with an existing user id.
     */
    @Test
    public void shouldReturnUserDetails() {

        final User user = UserStubFactory.createStubbedUser(USER_ID);

        when(userRepository.findOne(anyInt()))
                .thenReturn(Optional.of(user));

        final User result = sut.showUserDetails(USER_ID);

        // Verify response
        Assert.assertNotEquals("Result is null", result);
        Assert.assertEquals("Result is different than expected", user, result);

        // Verify mocks
        verify(userRepository, times(1)).findOne(USER_ID);
    }

    /**
     * This test verify that the user is updated correctly.
     */
    @Test
    public void shouldBeanUpdateExercise() {

        final User originalUser = UserStubFactory.createStubbedUser(USER_ID);
        final User updatedUser = UserStubFactory
                .createStubUserWithCustomFields(USER_ID,
                        SOME_NEW_USERNAME);

        when(userRepository.findOne(anyInt()))
                .thenReturn(Optional.of(originalUser));

        when(userRepository.save(any(User.class)))
                .thenReturn(updatedUser);

        final User result = sut.updateUser(updatedUser, USER_ID);

        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result is different than expected", result, updatedUser);

        verify(userRepository, times(1)).findOne(USER_ID);
        verify(userRepository, times(1)).save(updatedUser);
    }

    /**
     * This test verify that if the user with specified Id doesn't exist
     * the returned exception.
     */
    @Test
    public void shouldReturnNullBecauseUserIdDoesNotExist() {

        final User updatedUser = UserStubFactory
                .createStubUserWithCustomFields(USER_ID,
                        SOME_NEW_USERNAME);

        when(userRepository.findOne(anyInt()))
                .thenReturn(Optional.empty());

        try {
            sut.updateUser(updatedUser, USER_ID);
        } catch (EntityNotFoundException ex) {
            verify(userRepository, times(1)).findOne(USER_ID);
        }
    }

    /**
     * This test verify that Users performs correct interaction
     * when return a list of users.
     */
    @Test
    public void shouldReturnListOfUsers() {

        final List<User> userList = UserStubFactory.createStubbedUserList(4);

        when(userRepository.findAll()).thenReturn(userList);

        final List<User> result = new ArrayList<>(sut.getUsers());

        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result contents is different than expected.", userList, result);

        verify(userRepository, times(1)).findAll();
    }


    /**
     * This test verify that the expected list of exercise per user
     * is returned.
     */
    @Test
    public void shouldReturnListOfExercisesPerUser() {

        final User originalUser = UserStubFactory.createStubbedUser(USER_ID);
        final Collection<Exercise> exercises = ExerciseStubFactory.createStubExercisesList(3);

        when(userRepository.findOne(anyInt()))
                .thenReturn(Optional.of(originalUser));

        when(userRepository.getExercisesPerUser(anyInt()))
                .thenReturn(exercises);

        Collection<Exercise> result = sut.getExercisesPerUser(USER_ID);

        //Verify result
        Assert.assertNotNull("Result is null.", result);
        Assert.assertTrue("Result is empty.", !CollectionUtils.isEmpty(result));
        Assert.assertEquals("Result is different than expected.", exercises, result);

        // Verify mocks behaviour
        verify(userRepository, times(1)).findOne(USER_ID);
        verify(userRepository, times(1)).getExercisesPerUser(USER_ID);
    }


}
