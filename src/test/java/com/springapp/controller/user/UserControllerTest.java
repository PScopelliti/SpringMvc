package com.springapp.controller.user;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import com.springapp.service.user.UserResource;
import com.springapp.utils.IntegrationTestUtil;
import com.springapp.utils.exercise.ExerciseStubFactory;
import com.springapp.utils.user.UserStubFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
public class UserControllerTest {

    private static final Integer USER_ID = 123;
    private static final String SOME_NEW_USERNAME = "some_new_username";

    @Mock
    private UserResource userResource;

    @InjectMocks
    private UserController sut;

    private MockMvc mockMvc;

    @Before
    public void init() {
        // Setup Spring test in standalone mode
        mockMvc = standaloneSetup(sut).build();
    }

    /**
     * Test if the register API returns the correct response.
     *
     * @throws Exception
     */
    @Test
    public void shouldAddNewUserResponse() throws Exception {

        final User user = UserStubFactory.createStubbedUser(USER_ID);

        when(userResource.save(any(User.class)))
                .thenReturn(user);

        mockMvc.perform(post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/api/v1/users/" + USER_ID))
                .andExpect(jsonPath("id").value(user.getId().intValue()))
                .andExpect(jsonPath("username").value(user.getUsername()));

        // Verify mock
        verify(userResource, times(1)).save((user));
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

        when(userResource.showUserDetails(anyInt()))
                .thenReturn(user);

        mockMvc.perform(get("/api/v1/users/{id}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(user.getId().intValue()))
                .andExpect(jsonPath("username").value(user.getUsername()));

        verify(userResource, times(1)).showUserDetails(USER_ID);
    }

    /**
     * This test verify if the response to put is equals to expected.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnExpectedResponseToPut() throws Exception {

        final User updatedUser = UserStubFactory
                .createStubUserWithCustomFields(USER_ID,
                        SOME_NEW_USERNAME);

        when(userResource.updateUser(isA(User.class), anyInt()))
                .thenReturn(updatedUser);

        mockMvc.perform(put("/api/v1/users/{id}", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(updatedUser.getId().intValue()))
                .andExpect(jsonPath("username").value(updatedUser.getUsername()));

        verify(userResource, times(1)).updateUser(updatedUser, USER_ID);

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

        when(userResource.findUser(anyInt()))
                .thenReturn(originalUser);

        when(userResource.getExercisesPerUser(anyInt()))
                .thenReturn(exercises);

        mockMvc.perform(get("/api/v1/users/{userId}/exercises", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(exercises.get(0).getId().intValue()))
                .andExpect(jsonPath("$[0].description").value(exercises.get(0).getDescription()));

        // Verify mocks behaviour
        verify(userResource, times(1)).getExercisesPerUser(USER_ID);
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

        when(userResource.findUser(anyInt()))
                .thenReturn(originalUser);

        mockMvc.perform(delete("/api/v1/users/{id}", USER_ID))
                .andExpect(status().isOk());

        verify(userResource, times(1)).deleteUserById(USER_ID);
    }

    /**
     * This test verify that the controller return the expected
     * response for the specified request.
     *
     * @throws Exception
     */
    @Test
    public void shouldProcessGetCorrectly() throws Exception {

        final List<User> userList = UserStubFactory.createStubbedUserList(1);

        when(userResource.getUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(userList.get(0).getId().intValue()))
                .andExpect(jsonPath("$[0].username").value(userList.get(0).getUsername()));

        verify(userResource, times(1)).getUsers();
    }
}
