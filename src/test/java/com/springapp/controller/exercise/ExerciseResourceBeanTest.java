package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import com.springapp.utils.exercise.ExerciseStubFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class contains methods for verify that the Exercise controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExerciseResourceBeanTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseResourceBean sut;

    private MockMvc mockMvc;

    /**
     * This test verify that the controller return the expected
     * registration form page.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnRegistrationForm() throws Exception {

        mockMvc = standaloneSetup(sut).build();

        mockMvc.perform(get("/exercise/register"))
                .andExpect(view().name("exerciseRegisterForm"));
    }

    /**
     * This test verify that the controller return the expected
     * user id page.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnExerciseIdPage() throws Exception {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(1L);

        mockMvc = standaloneSetup(sut).build();

        when(exerciseRepository.save(exercise))
                .thenReturn(exercise);

        mockMvc.perform(post("/exercise/register")
                .param("name", "some_name")
                .param("id", String.valueOf(exercise.getId()))
                .param("description", "some_description"))
                .andExpect(redirectedUrl("/exercise/" + exercise.getId()));

        verify(exerciseRepository, times(1)).save(exercise);
    }

    /**
     * This test verify that the controller return the expected page
     * for selected exercise.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnExerciseDetails() throws Exception {

        final Exercise exercise = ExerciseStubFactory.createStubExercise(1L);

        mockMvc = standaloneSetup(sut).build();

        when(exerciseRepository.findOne(anyLong()))
                .thenReturn(Optional.of(exercise));

        mockMvc.perform(get("/exercise/12345"))
                .andExpect(view().name("exerciseDetails"))
                .andExpect(model().attributeExists("exercise"))
                .andExpect(model().attribute("exercise", exercise));

        verify(exerciseRepository, times(1)).findOne(12345L);
    }


}
