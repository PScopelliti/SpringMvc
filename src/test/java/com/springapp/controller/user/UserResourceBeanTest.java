package com.springapp.controller.user;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.repository.UserRepository;
import com.springapp.utils.IntegrationTestUtil;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class contains methods for verify that the User controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserResourceBeanTest {

    private static final Long USER_ID = 123L;
    private static final Long EXERCISE_ID = 12L;
    private static final String SOME_NEW_USERNAME = "some_new_username";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserResourceBean sut;

    private MockMvc mockMvc;

    @Before
    public void init() {
        // Setup Spring test in standalone mode
        mockMvc = standaloneSetup(sut).build();
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

        when(userRepository.findOne(anyLong()))
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

        final User user = UserStubFactory.createStubbedUser(1L);

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        final ResponseEntity<User> result = sut.processRegistration(user);

        //Verify result
        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result status is different than expected.", HttpStatus.CREATED, result.getStatusCode());
        Assert.assertEquals("Result body is different than expected.", user, result.getBody());

        // Verify mock
        verify(userRepository, times(1)).save(user);
    }

    /**
     * Test if the register API returns the correct response.
     *
     * @throws Exception
     */
    @Test
    public void shouldAddNewUserResponse() throws Exception {

        final User user = UserStubFactory.createStubbedUser(USER_ID);

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/user/" + USER_ID))
                .andExpect(jsonPath("id").value(user.getId().intValue()))
                .andExpect(jsonPath("username").value(user.getUsername()));

        // Verify mock
        verify(userRepository, times(1)).save((user));
    }

    /**
     * This test verify that the controller return the expected page
     * for selected user.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnUserDetailsResponse() throws Exception {

        final User user = UserStubFactory.createStubbedUser(USER_ID);

        when(userRepository.findOne(anyLong()))
                .thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/{id}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(user.getId().intValue()))
                .andExpect(jsonPath("username").value(user.getUsername()));

        verify(userRepository, times(1)).findOne(USER_ID);
    }

    /**
     * Verify if the controller return the correct response when
     * is called with an existing user id.
     */
    @Test
    public void shouldReturnUserDetails() {

        final User user = UserStubFactory.createStubbedUser(USER_ID);

        when(userRepository.findOne(anyLong()))
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

        when(userRepository.findOne(anyLong()))
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

        when(userRepository.findOne(anyLong()))
                .thenReturn(Optional.empty());

        try {
            sut.updateUser(updatedUser, USER_ID);
        } catch (EntityNotFoundException ex) {
            verify(userRepository, times(1)).findOne(USER_ID);
        }
    }

    /**
     * This test verify if the response to put is equals to expected.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnExpectedResponseToPut() throws Exception {

        final User originalUser = UserStubFactory.createStubbedUser(USER_ID);
        final User updatedUser = UserStubFactory
                .createStubUserWithCustomFields(USER_ID,
                        SOME_NEW_USERNAME);

        when(userRepository.findOne(anyLong()))
                .thenReturn(Optional.of(originalUser));

        when(userRepository.save(any(User.class)))
                .thenReturn(updatedUser);

        mockMvc.perform(put("/user/{id}", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(updatedUser.getId().intValue()))
                .andExpect(jsonPath("username").value(updatedUser.getUsername()));

        verify(userRepository, times(1)).findOne(USER_ID);
        verify(userRepository, times(1)).save(updatedUser);

    }

    /**
     * This test verify that controller return expected response to
     * a delete request.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnCorrectResponse() throws Exception {

        final User originalUser = UserStubFactory.createStubbedUser(USER_ID);

        when(userRepository.findOne(anyLong()))
                .thenReturn(Optional.of(originalUser));

        mockMvc.perform(delete("/user/{id}", USER_ID))
                .andExpect(status().isOk());

        verify(userRepository, times(1)).delete(USER_ID);
        verify(userRepository, times(1)).findOne(USER_ID);
    }

    /**
     * This test verify that the expected list of exercise per user
     * is returned.
     */
    @Test
    public void shouldReturnListOfExercisesPerUser() {

        final User originalUser = UserStubFactory.createStubbedUser(USER_ID);
        final Collection<Exercise> exercises = ExerciseStubFactory.createStubExercisesList(3);

        when(userRepository.findOne(anyLong()))
                .thenReturn(Optional.of(originalUser));

        when(userRepository.getExercisesPerUser(anyLong()))
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

    /**
     * This test verify that the controller returns the expected response
     * when we want get all exercises per user.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnListOfJsonExercises() throws Exception {


        final User originalUser = UserStubFactory.createStubbedUser(USER_ID);
        final List<Exercise> exercises = new ArrayList<>(ExerciseStubFactory.createStubExercisesList(3));

        when(userRepository.findOne(anyLong()))
                .thenReturn(Optional.of(originalUser));

        when(userRepository.getExercisesPerUser(anyLong()))
                .thenReturn(exercises);

        mockMvc.perform(get("/user/{userId}/exercises", USER_ID, EXERCISE_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(exercises.get(0).getId().intValue()))
                .andExpect(jsonPath("$[0].description").value(exercises.get(0).getDescription()));

        // Verify mocks behaviour
        verify(userRepository, times(1)).findOne(USER_ID);
        verify(userRepository, times(1)).getExercisesPerUser(USER_ID);

    }
}
