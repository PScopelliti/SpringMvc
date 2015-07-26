package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import com.springapp.utils.exercise.ExerciseStubFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class contains methods for verify that the Exercises controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExercisesResourceBeanTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExercisesResourceBean sut;

    private MockMvc mockMvc;

    @Before
    public void init() {
        // Setup Spring test in standalone mode
        mockMvc = standaloneSetup(sut).build();
    }

    /**
     * This test verify that exercise resource bean get all expected exercises.
     *
     * @throws Exception
     */
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

    /**
     * This verify that the controller return expected response for exercises request.
     *
     * @throws Exception
     */
    @Test
    public void shouldPerformGetCorrectly() throws Exception {

        final List<Exercise> expectedExercises = ExerciseStubFactory.createStubExercisesList(1);

        when(exerciseRepository.findAll()).thenReturn(expectedExercises);

        mockMvc.perform(get("/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(expectedExercises.get(0).getId().intValue()))
                .andExpect(jsonPath("$[0].name").value(expectedExercises.get(0).getName()))
                .andExpect(jsonPath("$[0].description").value(expectedExercises.get(0).getDescription()));

        verify(exerciseRepository, times(1)).findAll();

    }
}
