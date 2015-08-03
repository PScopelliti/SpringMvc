package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import com.springapp.utils.IntegrationTestUtil;
import com.springapp.utils.exercise.ExerciseStubFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class contains methods for verify that the Exercise controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExerciseResourceBeanTest {

    private static final Long EXERCISE_ID = 123L;

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseResourceBean sut;

    private MockMvc mockMvc;

    @Before
    public void init() {
        // Setup Spring test in standalone mode
        mockMvc = standaloneSetup(sut).build();
    }

    @After
    public void after() {
        //verifyNoMoreInteractions(exerciseRepository);
    }

    /**
     * Test if the controller returns the expected result when is called
     * for saving exercises.
     */
    @Test
    public void shouldAddNewExercise() {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(1L);

        when(exerciseRepository.save(any(Exercise.class)))
                .thenReturn(exercise);

        final Exercise result = sut.processRegistration(exercise);

        //Verify result
        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result is different than expected.", exercise, result);

        // Verify mock
        verify(exerciseRepository, times(1)).save(exercise);
    }

    /**
     * Test if the register API returns the correct response.
     *
     * @throws Exception
     */
    @Test
    public void shouldAddNewExerciseResponse() throws Exception {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(1L);

        when(exerciseRepository.save(any(Exercise.class)))
                .thenReturn(exercise);

        mockMvc.perform(post("/exercise/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(exercise.getId().intValue()))
                .andExpect(jsonPath("name").value(exercise.getName()))
                .andExpect(jsonPath("description").value(exercise.getDescription()));

        // Verify mock
        verify(exerciseRepository, times(1)).save((exercise));
    }

    /**
     * Verify if the controller return the correct response when
     * is called with an existing exercise id.
     */
    @Test
    public void shouldReturnExerciseDetails() {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(1L);

        when(exerciseRepository.findOne(anyLong()))
                .thenReturn(Optional.of(exercise));

        final Exercise result = sut.showExerciseDetails(EXERCISE_ID);

        // Verify response
        Assert.assertNotEquals("Result is null", result);
        Assert.assertEquals("Result is different than expected", exercise, result);

        // Verify mocks
        verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
    }

    /**
     * This test verify that the controller return the expected page
     * for selected exercise.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnExerciseDetailsResponse() throws Exception {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(1L);

        when(exerciseRepository.findOne(anyLong()))
                .thenReturn(Optional.of(exercise));

        mockMvc.perform(get("/exercise/{id}", EXERCISE_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(exercise.getId().intValue()))
                .andExpect(jsonPath("name").value(exercise.getName()))
                .andExpect(jsonPath("description").value(exercise.getDescription()));

        verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
    }

    /**
     * This test verify that exercise is deleted correctly.
     */
    @Test
    public void shouldRemoveOneExercise() {

        sut.deleteExerciseById(EXERCISE_ID);

        verify(exerciseRepository, times(1)).delete(EXERCISE_ID);
    }

    /**
     * This test verify that controller return expected response to
     * a delete request.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnCorrectResponse() throws Exception {

        mockMvc.perform(delete("/exercise/{id}", EXERCISE_ID))
                .andExpect(status().isOk());

        verify(exerciseRepository, times(1)).delete(EXERCISE_ID);
    }
}
