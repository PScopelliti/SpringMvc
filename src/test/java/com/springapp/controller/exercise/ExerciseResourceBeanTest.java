package com.springapp.controller.exercise;

import com.springapp.exception.ExerciseNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
 * This class contains methods for verify that the Exercise controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExerciseResourceBeanTest {

    private static final Long EXERCISE_ID = 123L;
    private static final String SOME_NEW_DESCRIPTION = "some_new_description";
    private static final String SOME_NEW_NAME = "some_new_name";

    private MockHttpServletRequest request;
    private MockHttpSession session;

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseResourceBean sut;

    private MockMvc mockMvc;

    @Before
    public void init() {
        // Setup Spring test in standalone mode
        this.mockMvc = standaloneSetup(sut).build();

        this.request = new MockHttpServletRequest();
        this.session = new MockHttpSession();
        this.request.setSession(session);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
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

        final Exercise exercise = ExerciseStubFactory.createStubExercise(1L);

        when(exerciseRepository.save(any(Exercise.class)))
                .thenReturn(exercise);

        final ResponseEntity<Exercise> result = sut.processRegistration(exercise);

        //Verify result
        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result status is different than expected.", HttpStatus.CREATED, result.getStatusCode());
        Assert.assertEquals("Result body is different than expected.", exercise, result.getBody());

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

        final Exercise exercise = ExerciseStubFactory.createStubExercise(EXERCISE_ID);

        when(exerciseRepository.save(any(Exercise.class)))
                .thenReturn(exercise);

        mockMvc.perform(post("/exercise/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/exercise/" + EXERCISE_ID))
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

        final Exercise exercise = ExerciseStubFactory.createStubExercise(1L);

        when(exerciseRepository.findOne(anyLong()))
                .thenReturn(Optional.of(exercise));

        sut.deleteExerciseById(EXERCISE_ID);

        verify(exerciseRepository, times(1)).delete(EXERCISE_ID);
        verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
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

        when(exerciseRepository.findOne(anyLong()))
                .thenReturn(Optional.of(originalEx));

        mockMvc.perform(delete("/exercise/{id}", EXERCISE_ID))
                .andExpect(status().isOk());

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

        when(exerciseRepository.findOne(anyLong()))
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

        when(exerciseRepository.findOne(anyLong()))
                .thenReturn(Optional.empty());

        try {
            sut.updateExercise(updatedEx, EXERCISE_ID);
        } catch (ExerciseNotFoundException ex) {
            verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
        }
    }

    /**
     * This test verify if the response to put is equals to expected.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnExpectedResponseToPut() throws Exception {

        final Exercise originalEx = ExerciseStubFactory.createStubExercise(EXERCISE_ID);
        final Exercise updatedEx = ExerciseStubFactory
                .createStubExerciseWithCustomFields(EXERCISE_ID,
                        SOME_NEW_DESCRIPTION,
                        SOME_NEW_NAME);

        when(exerciseRepository.findOne(anyLong()))
                .thenReturn(Optional.of(originalEx));

        when(exerciseRepository.save(any(Exercise.class)))
                .thenReturn(updatedEx);

        mockMvc.perform(put("/exercise/{id}", EXERCISE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(updatedEx)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(updatedEx.getId().intValue()))
                .andExpect(jsonPath("name").value(updatedEx.getName()))
                .andExpect(jsonPath("description").value(updatedEx.getDescription()));

        verify(exerciseRepository, times(1)).findOne(EXERCISE_ID);
        verify(exerciseRepository, times(1)).save(updatedEx);

    }
}
