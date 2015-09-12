package com.springapp.controller.userexercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.SimpleUserExercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.model.UserExercise;
import com.springapp.service.exercise.ExerciseResource;
import com.springapp.service.user.UserResource;
import com.springapp.service.userexercise.UserExerciseResource;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class contains methods for verify that the User Exercise controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserExerciseControllerTest {

    private static final Integer USER_ID = 123;
    private static final Integer EXERCISE_ID = 123;

    @Mock
    private ExerciseResource exerciseResource;

    @Mock
    private UserResource userResource;

    @Mock
    private UserExerciseResource userExerciseResource;

    @InjectMocks
    private UserExerciseController sut;

    private MockMvc mockMvc;

    @Before
    public void init() {
        // Setup Spring test in standalone mode
        mockMvc = standaloneSetup(sut).build();
    }

    @After
    public void after() {
        verifyNoMoreInteractions(userResource, exerciseResource, userExerciseResource);
    }

    @Test
    public void postExercisePerUserTest() throws Exception {

        final User user = UserStubFactory.createStubbedUser(USER_ID);
        final Exercise exercise = ExerciseStubFactory.createStubExercise(EXERCISE_ID);
        final UserExercise userExercise = UserExerciseStubFactory.createStubbedUserExercise(user, exercise);

        when(userResource.findUser(anyInt())).thenReturn(user);
        when(exerciseResource.findExercise(anyInt())).thenReturn(exercise);
        when(userExerciseResource.save(isA(UserExercise.class))).thenReturn(userExercise);

        mockMvc.perform(post("/users/{userId}/exercises/{exerciseId}", USER_ID, EXERCISE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/users/" + USER_ID + "/exercises/" + EXERCISE_ID));

        verify(userResource, times(1)).findUser(USER_ID);
        verify(exerciseResource, times(1)).findExercise(EXERCISE_ID);
        verify(userExerciseResource, times(1)).save(isA(UserExercise.class));
    }

    @Test
    public void deleteExercisePerUserTest() throws Exception {

        final User user = UserStubFactory.createStubbedUser(USER_ID);
        final Exercise exercise = ExerciseStubFactory.createStubExercise(EXERCISE_ID);

        when(userResource.findUser(anyInt())).thenReturn(user);
        when(exerciseResource.findExercise(anyInt())).thenReturn(exercise);

        mockMvc.perform(delete("/users/{userId}/exercises/{exerciseId}", USER_ID, EXERCISE_ID))
                .andExpect(status().isNoContent());

        verify(userResource, times(1)).findUser(USER_ID);
        verify(exerciseResource, times(1)).findExercise(EXERCISE_ID);
        verify(userExerciseResource, times(1)).deleteUserExercise(exercise, user);
    }

    @Test
    public void getUserExercisesTest() throws Exception {

        final Collection<SimpleUserExercise> simpleUserExercises = UserExerciseStubFactory.createStubbedSimpleUserExerciseCollection(3);
        final ArrayList<SimpleUserExercise> listOfExercises = new ArrayList<>(simpleUserExercises);

        when(userExerciseResource.getUsersExercises()).thenReturn(simpleUserExercises);

        mockMvc.perform(get("/users/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId").value(listOfExercises.get(0).getUserId()))
                .andExpect(jsonPath("$[0].exerciseId").value(listOfExercises.get(0).getExerciseId()));

        verify(userExerciseResource, times(1)).getUsersExercises();
    }
}
