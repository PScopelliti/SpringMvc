package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.service.exercise.ExerciseResource;
import com.springapp.utils.IntegrationTestUtil;
import com.springapp.utils.exercise.ExerciseStubFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
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
 * This class contains methods for verify that the Exercise controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExerciseControllerTest {

    private static final Long EXERCISE_ID = 123L;
    private static final String SOME_NEW_DESCRIPTION = "some_new_description";
    private static final String SOME_NEW_NAME = "some_new_name";

    private MockHttpServletRequest request;
    private MockHttpSession session;
    private MockMvc mockMvc;

    @Mock
    private ExerciseResource exerciseResource;

    @InjectMocks
    private ExerciseController sut;

    @Before
    public void init() {

        // Setup Spring test in standalone mode
        this.mockMvc = standaloneSetup(sut).build();

        this.request = new MockHttpServletRequest();
        this.session = new MockHttpSession();
        this.request.setSession(session);
    }

    /**
     * Test if the register API returns the correct response.
     *
     * @throws Exception
     */
    @Test
    public void shouldAddNewExerciseResponse() throws Exception {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(EXERCISE_ID);

        when(exerciseResource.save(any(Exercise.class)))
                .thenReturn(exercise);

        mockMvc.perform(post("/exercises/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/exercises/" + EXERCISE_ID))
                .andExpect(jsonPath("id").value(exercise.getId().intValue()))
                .andExpect(jsonPath("name").value(exercise.getName()))
                .andExpect(jsonPath("description").value(exercise.getDescription()));

        // Verify mock
        verify(exerciseResource, times(1)).save((exercise));
    }

    /**
     * This test verify that the controller return the expected page
     * for selected exercise.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnExerciseDetailsResponse() throws Exception {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(EXERCISE_ID);

        when(exerciseResource.findExercise(anyLong()))
                .thenReturn(exercise);

        mockMvc.perform(get("/exercises/{id}", EXERCISE_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(exercise.getId().intValue()))
                .andExpect(jsonPath("name").value(exercise.getName()))
                .andExpect(jsonPath("description").value(exercise.getDescription()));

        verify(exerciseResource, times(1)).findExercise(EXERCISE_ID);
    }

    /**
     * This test verify that controller return expected response to
     * a delete request.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnCorrectResponse() throws Exception {

        final Exercise originalEx = ExerciseStubFactory.createStubExercise(EXERCISE_ID);

        when(exerciseResource.findExercise(anyLong()))
                .thenReturn(originalEx);

        mockMvc.perform(delete("/exercises/{id}", EXERCISE_ID))
                .andExpect(status().isOk());

        verify(exerciseResource, times(1)).deleteExerciseById(EXERCISE_ID);
    }

    /**
     * This test verify if the response to put is equals to expected.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnExpectedResponseToPut() throws Exception {

        final Exercise updatedEx = ExerciseStubFactory
                .createStubExerciseWithCustomFields(EXERCISE_ID,
                        SOME_NEW_DESCRIPTION,
                        SOME_NEW_NAME);

        when(exerciseResource.updateExercise(isA(Exercise.class), anyLong()))
                .thenReturn(updatedEx);

        mockMvc.perform(put("/exercises/{id}", EXERCISE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(updatedEx)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(updatedEx.getId().intValue()))
                .andExpect(jsonPath("name").value(updatedEx.getName()))
                .andExpect(jsonPath("description").value(updatedEx.getDescription()));

        verify(exerciseResource, times(1)).updateExercise(updatedEx, EXERCISE_ID);
    }

    /**
     * This verify that the controller return expected response for exercises request.
     *
     * @throws Exception
     */
    @Test
    public void shouldPerformGetCorrectly() throws Exception {

        final List<Exercise> expectedExercises = new ArrayList<>(ExerciseStubFactory.createStubExercisesList(1));

        when(exerciseResource.getExercises())
                .thenReturn(expectedExercises);

        mockMvc.perform(get("/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(expectedExercises.get(0).getId().intValue()))
                .andExpect(jsonPath("$[0].name").value(expectedExercises.get(0).getName()))
                .andExpect(jsonPath("$[0].description").value(expectedExercises.get(0).getDescription()));

        verify(exerciseResource, times(1)).getExercises();
    }

}
